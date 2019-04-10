package dev.ryz3n.util

import kotlinx.coroutines.delay
import org.jetbrains.annotations.NotNull
import java.text.SimpleDateFormat


object DateUtil {

    fun igPostDate2Timestamp(@NotNull postDate: String): Long {
        val sub = postDate.subSequence(0, 19).toString()
        val formatDate = sub.replace("T", " ")
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatDate).time / 1000
    }
}


fun main(args: Array<String>) {
    Runtime.getRuntime().exec("python E:/KTCODE/instagram-crawler/crawler.py posts_full -u futurehousemusic -n 5 -o E:/KTCODE/instagram-crawler/futurehousemusic.txt")
}