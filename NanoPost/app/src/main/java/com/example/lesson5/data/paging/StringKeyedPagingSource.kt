package com.example.lesson5.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lesson5.data.NanoPostApiService
import com.example.lesson5.data.model.ApiPost
import java.lang.Exception
import java.util.concurrent.CancellationException

class StringKeyedPagingSource(

    private val profileId: String?,
    private val apiService: NanoPostApiService,

) : PagingSource<String, ApiPost>() {
    override fun getRefreshKey(state: PagingState<String, ApiPost>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ApiPost> = try {

        val response = apiService.getPosts(profileId ?: "", params.loadSize, params.key)

        LoadResult.Page(
            response.items,
            prevKey = null,
            nextKey = response.offset?.takeIf {
                (it.toInt() + params.loadSize) - response.total > 0
            }
        )
    }

    catch (e: CancellationException) {
        throw e
    }
    catch (e: Exception) {
        LoadResult.Error(e)
    }

}