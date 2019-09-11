@file:Suppress("SpellCheckingInspection")

package dev.ryz3n

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.gson.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.*
import dev.ryz3n.database.DatabaseFactory
import dev.ryz3n.route.igPost
import io.ktor.jackson.*
import kotlinx.coroutines.*
import org.jetbrains.annotations.NotNull
import java.io.File

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {

        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val crawList: MutableList<String> = ArrayList()
    File(".\\crawl.txt").forEachLine {
        crawList.add(it)
    }

    DatabaseFactory.init(
        crawList
    )

    install(Routing) {
        igPost(crawList)
    }

    launch {
        updateDB(crawList)
    }

}

private suspend fun updateDB(@NotNull crawList: MutableList<String>, cycle: Long = 2 * 60 * 1000L) {
    while (true) {
        delay(cycle)
        println("......waiting for update......")

        DatabaseFactory.update(crawList)
        println("......updating......")
    }
}

