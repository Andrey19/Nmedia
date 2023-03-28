package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 0,
    val shared: Int = 1999,
    val likedByMe: Boolean = false,
    val video: String = ""
)

