package dev.ryz3n.route

import dev.ryz3n.model.BasePostsTable
import dev.ryz3n.service.IgPostService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import org.jetbrains.annotations.NotNull

fun Route.igPost(@NotNull tableMap: MutableMap<String, BasePostsTable>) {

    route("/get") {
        tableMap.forEach { path, currentTable ->
            get("/$path") {
                val igPostService = IgPostService(currentTable)
                    call.respond(
                        igPostService.get()
                    )
            }
        }
    }
}