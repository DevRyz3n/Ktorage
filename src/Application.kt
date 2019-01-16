@file:Suppress("SpellCheckingInspection")

package dev.ryz3n

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.gson.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.*
import dev.ryz3n.database.DatabaseFactory
import dev.ryz3n.model.FutureHouseMusicTable
import dev.ryz3n.model.NcsPostsTable
import dev.ryz3n.route.igPost
import io.ktor.jackson.*

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

    val tableMap = mutableMapOf(
        "nocopyrightsounds" to NcsPostsTable,
        "futurehousemusic" to FutureHouseMusicTable
    )

    DatabaseFactory.init(
        tableMap
    )

    install(Routing) {
        igPost(tableMap)
    }
}

