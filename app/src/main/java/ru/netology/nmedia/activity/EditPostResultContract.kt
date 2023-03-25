package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.dto.Post

class EditPostResultContract : ActivityResultContract<Post, Post?>() {

    override fun createIntent(context: Context, input: Post): Intent {
        val intent = Intent(context, EditPostActivity::class.java)
        intent.putExtra("content", input.content)
        intent.putExtra("video", input.video)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Post? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)
            Post(
                0, "", intent?.getStringExtra("content").toString(), "",
                0, 0,
                false, intent?.getStringExtra("video").toString()
            )
        } else {
            null
        }
}
