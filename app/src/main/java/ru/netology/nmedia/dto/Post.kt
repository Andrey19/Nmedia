package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 0,
    var shared: Int = 0,
    var likedByMe: Boolean = false
) {
    fun formatValue(value: Int): String {
        when (value) {
            in 0..999 -> return value.toString()
            in 1000..9999 -> {
                var cur: Int = value / 100
                if (cur % 10 == 0) {
                    return (cur / 10).toString() + "K"
                }
                return ((value / 100).toDouble() / 10).toString() + "K"
            }
            in 10000..999999 -> return (value / 1000).toString() + "K"
            else -> {
                var cur: Int = value / 100_000
                if (cur % 10 == 0) {
                    return (cur / 10).toString() + "M"
                }
                return ((value / 100_000).toDouble() / 10).toString() + "M"
            }
        }
    }
}

