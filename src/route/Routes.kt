package dev.ryz3n.route

import dev.ryz3n.service.IgPostService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.igPost(igPostService: IgPostService){
    route("/demo"){
        get ("/get"){
            call.respond(igPostService.get())
        }
    }
}