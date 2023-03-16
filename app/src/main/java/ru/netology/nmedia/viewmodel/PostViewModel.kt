package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImplementation

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    likes = 0,
    shared = 0,
    published = ""
)
class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImplementation()

    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
    fun like(id:Long) = repository.likeById(id)
    fun shared(id:Long) = repository.sharedById(id)
    fun removeById(id:Long) = repository.removeById(id)

}
