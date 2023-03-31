package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryFileImpl


private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    likes = 0,
    shared = 0,
    published = "",
    video = ""
)
class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PostRepository = PostRepositoryFileImpl(application)

    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    val viewed = MutableLiveData(empty)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty

    }
    fun setView(post: Post){
        viewed.value = post
    }

    fun getView(): Post{
        return viewed.value!!
    }
    fun getPostById(id: Long):Post{
        return repository.getPostById(id)
    }
    fun cancel() {
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
    fun changeVideo(video: String) {
        val text = video.trim()
        if (edited.value?.video == text) {
            return
        }
        edited.value = edited.value?.copy(video = text)
    }
    fun like(id:Long) = repository.likeById(id)
    fun shared(id:Long) = repository.sharedById(id)
    fun removeById(id:Long) = repository.removeById(id)

}
