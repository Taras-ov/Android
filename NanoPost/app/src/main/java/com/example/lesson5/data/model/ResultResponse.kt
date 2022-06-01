package com.example.lesson5.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultResponse(
    val result: Boolean
)