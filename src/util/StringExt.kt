@file:Suppress("SpellCheckingInspection")

package dev.ryz3n.util

import org.jetbrains.annotations.NotNull


fun String.getMusicName(@NotNull records: String): String {

    when (records.trim()) {
        "ncs", "nocopyrightsounds" -> {
            var music = ""
                if (this.toLowerCase().endsWith("bio!")){
                val tmp = this.substring(this.indexOf("'")+1)
                music = tmp.substring(0, tmp.indexOf("'"))
            }

            return "ncs - $music"
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