package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.EditPostFragment.Companion.content
import ru.netology.nmedia.activity.EditPostFragment.Companion.video
import ru.netology.nmedia.databinding.FragmetViewPostBinding
import ru.netology.nmedia.util.LongArg
import ru.netology.nmedia.util.Utility
import ru.netology.nmedia.viewmodel.PostViewModel


class ViewPostFragment : Fragment() {

    companion object {
        var Bundle.id: Long by LongArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmetViewPostBinding.inflate(
            inflater,
            container,
            false
        )
        val postId = arguments?.id!!
        var post =  viewModel.getPostById(postId)
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            share.text = Utility.formatValue(post.shared)
            like.isChecked = post.likedByMe
            like.text = Utility.formatValue(post.likes)
            if (post.video.isEmpty()){
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
                                viewModel.removeById(post.id)
                                findNavController().navigateUp()
                                true
                            }
                            R.id.edit -> {
                                viewModel.edit(post)
                                findNavController().navigate(R.id.action_viewPostFragment_to_editPostFragment,
                                    Bundle().apply {
                                        content = post.content
                                        video = post.video
                                    }
                                )
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }

            viewModel.data.observe(viewLifecycleOwner) {
                post =  viewModel.getPostById(postId)
                if (post.id > 0 ) {
                    like.isChecked = post.likedByMe
                    like.text = Utility.formatValue(post.likes)
                    share.text = Utility.formatValue(post.shared)
                }
            }

            like.setOnClickListener {
                viewModel.like(post.id)

            }

            share.setOnClickListener {
                viewModel.shared(post.id)
            }
            video.setOnClickListener {
                val url = post.video
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }

            videoImage.setOnClickListener {
                val url = post.video
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }

        }
        return binding.root
    }
}
