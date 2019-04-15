package dev.ryz3n.route

import dev.ryz3n.model.BasePostsTable
import dev.ryz3n.service.IgPostService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import org.jetbrains.annotations.NotNull

fun Route.igPost(@NotNull crawList: MutableList<String>) {

    val igPostService = IgPostService(BasePostsTable())

    route("/get") {
        get("/all") {
            call.respond(
                igPostService.getAll()
            )
        }

        crawList.forEach { path ->
            get("/$path") {
                call.respond(
                    igPostService.get(path)
                )
            }
        }
    }
}