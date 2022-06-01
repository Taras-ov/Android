package com.example.lesson5.data

import com.example.lesson5.data.model.ApiImage
import com.example.lesson5.data.model.ApiPost
import com.example.lesson5.data.model.ApiProfile
import com.example.lesson5.data.model.ResultResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface NanoPostApiService {

    @GET("profile/{profileId}")
    suspend fun getUserProfile(
        @Path("profileId") profileId: String?,
    ) : ApiProfile

    @GET("posts/{profileId}")
    suspend fun getPosts(
        @Path("profileId") profileId: String?,
        @Query("count") count: Int,
        @Query("offset") offset: String?,
    ) : PagedDataResponse<ApiPost>

    @GET("post/{postId}")
    suspend fun getPost(
        @Path("postId") postId: String,
    ) : ApiPost

    @PUT("post")
    @Multipart
    suspend fun createPost(
        @Part("text") text: RequestBody?,
        @Part image1: MultipartBody.Part?,
        @Part image2: MultipartBody.Part?,
        @Part image3: MultipartBody.Part?,
        @Part image4: MultipartBody.Part?
    ) : ApiPost

    @DELETE("/api/v1/post/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: String,
    ) : ResultResponse

    @GET("/api/v1/image/{imageId}")
    suspend fun getImage(
        @Path("imageId") imageId: String
    ) : ApiImage

    @GET("/api/v1/images/{profileId}")
    suspend fun getProfileImages(
        @Path("profileId") profileId: String,
        @Query("count") count: Int,
        @Query("offset") offset: String?
    ) : PagedDataResponse<ApiImage>

    @DELETE("/api/v1/image/{imageId}")
    suspend fun deleteImage(
        @Path("imageId") imageId: String
    ) : ResultResponse
}