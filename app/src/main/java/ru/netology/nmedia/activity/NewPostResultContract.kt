package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract


class NewPostResultContract : ActivityResultContract<Unit, Pair<String,String>?>() {

    override fun createIntent(context: Context, input: Unit): Intent =
        Intent(context, NewPostActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): Pair<String,String>? =
        if (resultCode == Activity.RESULT_OK) {
            Pair(intent?.getStringExtra("content").toString(),
                intent?.getStringExtra("video").toString())
        } else {
            null
        }
}
