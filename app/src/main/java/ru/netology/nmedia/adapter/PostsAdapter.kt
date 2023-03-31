package ru.netology.nmedia.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.Utility

typealias OnLikeListener = (post: Post) -> Unit

interface OnInteractionListener {
    fun onView(post: Post) {}
    fun onLike(post: Post) {}
    fun onShared(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onVideo(post: Post) {}
}



class PostsAdapter(private val onInteractionListener: OnInteractionListener ) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            share.text = Utility.formatValue(post.shared)
            like.isChecked = post.likedByMe
            like.text = Utility.formatValue(post.likes)
            if (post.video.isNullOrEmpty()){
                videoGroup.visibility = View.GONE
            } else{
                videoGroup.visibility = View.VISIBLE
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
            content.setOnClickListener{
                onInteractionListener.onView(post)
            }
            root.setOnClickListener{
                onInteractionListener.onView(post)
            }
            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            share.setOnClickListener {
                onInteractionListener.onShared(post)
            }
            video.setOnClickListener {
                onInteractionListener.onVideo(post)
            }
            videoImage.setOnClickListener {
                onInteractionListener.onVideo(post)
            }

        }
    }
}
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
