package dev.ryz3n.model


data class IgPostsFullByJson(
    val comments: List<CommentByJson>,
    val content: String,
    val datetime: String,
    val img_urls: List<String>,
    val vdo_urls: List<String>,
    val key: String
)

data class CommentByJson(
    val author: String,
    val comment: String
)

data class IgPostsFull(
    val _id: Int,
    val post_key: String,
    val post_from: String,
    val post_author: String,
    val post_content: String,
    val post_date: Long,
    val post_img_list: List<String>,
    val post_vdo_list: List<String>
)


