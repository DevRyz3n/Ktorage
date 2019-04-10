@file:Suppress("SpellCheckingInspection")

package dev.ryz3n

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.gson.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.*
import dev.ryz3n.database.DatabaseFactory
import dev.ryz3n.model.BasePostsTable
import dev.ryz3n.model.FutureHouseMusicPostsTable
import dev.ryz3n.route.igPost
import io.ktor.jackson.*
import kotlinx.coroutines.*
import org.jetbrains.annotations.NotNull

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }

        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val tableMap: MutableMap<String, BasePostsTable> = mutableMapOf(
        "futurehousemusic" to FutureHouseMusicPostsTable
    )

    DatabaseFactory.init(
        tableMap
    )

    install(Routing) {
        igPost(tableMap)
    }

    launch {
        //runCrawler(tableMap)
        updateDB(tableMap)
    }


}

private suspend fun updateDB(@NotNull tableMap: MutableMap<String, BasePostsTable>, cycle: Long = 1 * 60 * 1000L) {
    while (true) {
        delay(cycle)
        println("......waiting for update......")

        DatabaseFactory.update(tableMap)
        println("......updating......")
    }
}

/*suspend fun runCrawler(@NotNull tableMap: MutableMap<String, BasePostsTable>) {
    while (true) {
        GlobalScope.launch {
            async {
                runCmd("futurehousemusic")
            }.await()
        }
    }
}

fun runCmd(@NotNull t: String) =
    ProcessBuilder("cmd.exe", "/c", "python .\\crawler\\crawler.py posts_full -u $t -n 5 -o .\\crawler\\$t.txt").start()*/
