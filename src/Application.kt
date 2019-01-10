package dev.ryz3n

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.*
import dev.ryz3n.database.DatabaseFactory
import dev.ryz3n.route.igPost
import dev.ryz3n.service.IgPostService
import io.ktor.jackson.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*

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

    DatabaseFactory.init()
    // val client = HttpClient(Apache) {}


    val igPostService = IgPostService()

    install(Routing){
        igPost(igPostService)
    }
}

