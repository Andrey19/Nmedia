package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 0,
    val shared: Int = 0,
    val likedByMe: Boolean,
    val video: String = ""
) {
    fun toDto() = Post(id, author, content, published, likes,shared,likedByMe, video)

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(dto.id, dto.author, dto.content, dto.published, dto.likes ,dto.shared,dto.likedByMe ,dto.video)

    }
}

