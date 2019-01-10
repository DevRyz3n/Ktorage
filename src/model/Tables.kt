@file:Suppress("ObjectPropertyName")

package dev.ryz3n.model

import org.jetbrains.exposed.sql.Table

object PostsTable : Table() {
    val _id = integer("_id").primaryKey().autoIncrement()
    val post_key = varchar("post_key", 255).uniqueIndex()
    val post_from = varchar("post_from", 255)
    val post_author = varchar("post_author", 255)
    val post_content = varchar("post_content", 255)
    val post_date = long("post_date")
    val post_img_0 = varchar("post_img_0", 255)
    val post_img_1 = varchar("post_img_1", 255)
    val post_img_2 = varchar("post_img_2", 255)
    val post_img_3 = varchar("post_img_3", 255)
    val post_img_4 = varchar("post_img_4", 255)
    val post_img_5 = varchar("post_img_5", 255)
    val post_img_6 = varchar("post_img_6", 255)
    val post_img_7 = varchar("post_img_7", 255)
    val post_img_8 = varchar("post_img_8", 255)
    val post_img_9 = varchar("post_img_9", 255)
}