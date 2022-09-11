package ru.axdar.data.news.repository

data class News(
    val id: String,
    val author: String = "",
    val title: String = "",
    val content: String = "",
    val imageUrl: String = "",
)
