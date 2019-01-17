package dev.ryz3n.util

import org.jetbrains.annotations.NotNull
import java.io.File

object CrawlerTxtUtil {
    fun readTxtFile(@NotNull filePath: String): String {
        val content = StringBuffer("")
        val file = File(filePath)
        file.forEachLine {
            content.append(it)
        }
        return content.toString()
    }
}
