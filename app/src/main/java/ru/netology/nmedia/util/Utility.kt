package ru.netology.nmedia.util

class Utility {
    companion object Factory {

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
}
