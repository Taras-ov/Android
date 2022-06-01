package com.example.lesson5.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiImageSize(
    val width: Long,
    val height: Long,
    val url: String
)