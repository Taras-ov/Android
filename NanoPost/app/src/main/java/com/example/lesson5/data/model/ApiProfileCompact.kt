package com.example.lesson5.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiProfileCompact(
    val id: String,
    val username: String,
    val displayName: String?,
    val avatarUrl: String?,
    val subscribed: Boolean,
)
