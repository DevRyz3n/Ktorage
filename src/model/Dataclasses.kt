package dev.ryz3n.model


data class IgPostsFullByJson(
    val comments: List<CommentByJson>,
    val content: String,
    val datetime: String,
    val img_urls: List<String>,
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
    val post_date: String,
    val post_img_0: String,
    val post_img_1: String,
    val post_img_2: String,
    val post_img_3: String,
    val post_img_4: String,
    val post_img_5: String,
    val post_img_6: String,
    val post_img_7: String,
    val post_img_8: String,
    val post_img_9: String
)


