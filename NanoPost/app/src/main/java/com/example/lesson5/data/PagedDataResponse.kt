package com.example.lesson5.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagedDataResponse<T>(
    val count: Int,
    val total: Int,
    val offset: String?,
    val items: List<T>,
)
