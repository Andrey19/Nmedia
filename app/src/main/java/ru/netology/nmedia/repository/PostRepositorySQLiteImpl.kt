package ru.netology.nmedia.repository


import androidx.lifecycle.Transformations
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.entity.PostEntity

class PostRepositorySQLiteImpl(
    private val dao: PostDao
) : PostRepository {

    override fun getAll() = Transformations.map(dao.getAll()) { list ->
        list.map {
            it.toDto()
        }
    }

    override fun likeById(id: Long) {
        dao.likeById(id)
    }

    override fun save(post: Post) {
        dao.save(PostEntity.fromDto(post))
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }

    override fun sharedById(id: Long) {
        dao.sharedById(id)
    }

    override fun getPostById(id: Long) :Post {
      return dao.getPostById(id).toDto()
    }


}

