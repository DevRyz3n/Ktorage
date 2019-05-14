package dev.ryz3n.route

import dev.ryz3n.model.BaseAuthorTable
import dev.ryz3n.model.BasePostsTable
import dev.ryz3n.model.IgPostsFull
import dev.ryz3n.service.IgAuthorService
import dev.ryz3n.service.IgPostService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import org.jetbrains.annotations.NotNull

fun Route.igPost(@NotNull crawList: MutableList<String>) {

    val igPostService = IgPostService(BasePostsTable())
    val igAuthorService = IgAuthorService(BaseAuthorTable())

    route("/get") {
        get("/all") {
            call.respond(igPostService.getAll())
        }

        get("/hot") {
            val all = igPostService.getAll()
            val videoOnly = ArrayList<IgPostsFull>()
            all.forEach { post ->
                if (!post.post_vdo_list.isNullOrEmpty()) {
                    videoOnly.add(post)
                }
            }
            call.respond(videoOnly)
        }

        get("/author/all"){
            call.respond(igAuthorService.getAll())
        }

        crawList.forEach { path ->
            get("/author/$path") {
                call.respond(
                    igAuthorService.get(path)
                )
            }

            get("/$path") {
                call.respond(
                    igPostService.get(path)
                )
            }
        }
    }
}