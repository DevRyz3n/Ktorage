package dev.ryz3n.model


data class IgPostsFullByJson(
    val comments: List<CommentByJson>,
    val caption: String,
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
    val post_author: String,
    val post_content: String,
    val post_music: String,
    val post_date: Long,
    val post_img_list: List<String>,
    val post_vdo_list: List<String>
)

data class IgAuthorByJson(
    val name: String,
    val desc: String,
    val photo_url: String
)

data class IgAuthor(
    val _id: Int,
    val author_avatar: String,
    val author_name: String,
    val author_ig_name: String,
    val author_info: String
)
