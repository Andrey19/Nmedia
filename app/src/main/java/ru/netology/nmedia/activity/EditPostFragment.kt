package ru.netology.nmedia.activity


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentEditPostBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel


class EditPostFragment : Fragment() {

    companion object {
        var Bundle.content: String? by StringArg
        var Bundle.video: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditPostBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.content?.let(binding.edit::setText)
        arguments?.video?.let(binding.videoLink::setText)
        binding.edit.requestFocus()
        binding.cancelButton.setOnClickListener {
            viewModel.cancel()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        binding.ok.setOnClickListener {
            if (binding.edit.text.isNullOrBlank()) {
                viewModel.cancel()
                AndroidUtils.hideKeyboard(requireView())
                findNavController().navigateUp()
            } else {
                viewModel.changeContent(binding.edit.text.toString())
                viewModel.changeVideo(binding.videoLink.text.toString())
                viewModel.save()
                AndroidUtils.hideKeyboard(requireView())
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancel()
    }
}
