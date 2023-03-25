package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityEditPostBinding


class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val content = intent?.getStringExtra("content")
        val video = intent?.getStringExtra("video")
        binding.edit.setText(content)
        binding.videoLink.setText(video)
        binding.edit.requestFocus()
        binding.cancelButton.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
        binding.ok.setOnClickListener {
            val intent = Intent()
            if (binding.edit.text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                intent.putExtra("content", binding.edit.text.toString())
                intent.putExtra("video", binding.videoLink.text.toString())
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}
