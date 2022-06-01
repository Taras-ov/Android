package com.example.lesson5.model

data class Post(
    val id: String,
    val owner: ProfileCompact,
    val dateCreated: Long,
    val text: String?,
    val images: List<Image>
)
