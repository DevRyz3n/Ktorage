@file:Suppress("SpellCheckingInspection")

package dev.ryz3n.util

import org.jetbrains.annotations.NotNull


fun String.getMusicName(@NotNull records: String): String {

    when (records.trim()) {
        "ncs", "nocopyrightsounds" -> {
            val content = this.replace('!', ' ').replace('“', '‘').replace('”', '’').trim()

            var startIndex = 0
            var endIndex = content.lastIndex
            if (content.endsWith("io)")) {
                if (content.find { it == '‘' } == null) return ""

                content.forEachIndexed { index, c ->
                    if (c == '‘') startIndex = index
                    if (c == '’') endIndex = index
                }
            }
            return "ncs - " + content.substring(startIndex + 1, endIndex)
        }

        "futurehousemusic" -> {
            val split = this.split("Track:")
            if (split[split.lastIndex] == this) return ""

            return split[split.lastIndex].trim()
        }

        else -> {
            return ""
        }
    }
}