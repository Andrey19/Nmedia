package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.dto.Post

class NewPostResultContract : ActivityResultContract<Unit, Post?>() {

    override fun createIntent(context: Context, input: Unit): Intent =
        Intent(context, NewPostActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): Post? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)
            Post(0,"",intent?.getStringExtra("content").toString(),"",
                0,0,
                false,intent?.getStringExtra("video").toString())
        } else {
            null
        }
}
