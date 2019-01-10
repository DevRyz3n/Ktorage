package dev.ryz3n.model

import org.jetbrains.exposed.sql.Table

object PostsTable : Table() {
    val _id = integer("_id").primaryKey().autoIncrement()
    val post_key = varchar("post_key", 255).uniqueIndex()
    val post_from = varchar("post_from", 255)
    val post_author = varchar("post_author", 255)
    val post_content = varchar("post_content", 255)
    val post_date = varchar("post_date", 255)
    val post_img_0 = PostsTable.varchar("post_img_0", 255)
    val post_img_1 = PostsTable.varchar("post_img_1", 255)
    val post_img_2 = PostsTable.varchar("post_img_2", 255)
    val post_img_3 = PostsTable.varchar("post_img_3", 255)
    val post_img_4 = PostsTable.varchar("post_img_4", 255)
    val post_img_5 = PostsTable.varchar("post_img_5", 255)
    val post_img_6 = PostsTable.varchar("post_img_6", 255)
    val post_img_7 = PostsTable.varchar("post_img_7", 255)
    val post_img_8 = PostsTable.varchar("post_img_8", 255)
    val post_img_9 = PostsTable.varchar("post_img_9", 255)
}