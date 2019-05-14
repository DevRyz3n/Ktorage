package dev.ryz3n.util

import org.jetbrains.annotations.NotNull
import java.io.File
import java.io.IOException

object CrawlerTxtUtil {
    fun readTxtFile(@NotNull filePath: String): String {
        val content = StringBuffer("")
        val file = File(filePath)
        if (file.exists()) {
            file.forEachLine {
                content.append(it)
            }
        } else {
            file.createNewFile()
        }
        return content.toString()
    }
}
