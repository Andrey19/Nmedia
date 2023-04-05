package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositorySQLiteImpl


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

    private val repository: PostRepository = PostRepositorySQLiteImpl(
        AppDb.getInstance(application).postDao
    )

    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    private var draft = Pair<String,String>("","")

    fun getDraft() : Pair<String,String>{
        return draft
    }
    fun saveDraft(values: Pair<String,String>){
        draft = values
    }
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty

    }

    fun getPostById(id: Long):Post{
        val post = repository.getPostById(id)
        if (post != null ){
            return post
        }
        return empty
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
