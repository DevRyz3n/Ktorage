package dev.ryz3n.service

import dev.ryz3n.model.IgPostsFull
import dev.ryz3n.database.DatabaseFactory.dbQuery
import dev.ryz3n.model.PostsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class IgPostService : BaseService<IgPostsFull>() {

    override suspend fun get(): List<IgPostsFull> = dbQuery {
        PostsTable.selectAll().map { to(it) }.sortedByDescending { it.post_date }
    }

    override suspend fun add(list: List<IgPostsFull>) = dbQuery {
        list.forEach { igPostsFull ->
            PostsTable.insert { table ->
                table[post_key] = igPostsFull.post_key
                table[post_from] = igPostsFull.post_from
                table[post_author] = igPostsFull.post_author
                table[post_content] = igPostsFull.post_content
                table[post_date] = igPostsFull.post_date

                table[post_img_0] = igPostsFull.post_img_list[0]
                table[post_img_1] = igPostsFull.post_img_list[1]
                table[post_img_2] = igPostsFull.post_img_list[2]
                table[post_img_3] = igPostsFull.post_img_list[3]
                table[post_img_4] = igPostsFull.post_img_list[4]
                table[post_img_5] = igPostsFull.post_img_list[5]
                table[post_img_6] = igPostsFull.post_img_list[6]
                table[post_img_7] = igPostsFull.post_img_list[7]
                table[post_img_8] = igPostsFull.post_img_list[8]
                table[post_img_9] = igPostsFull.post_img_list[9]

                table[post_vdo_0] = igPostsFull.post_img_list[0]
                table[post_vdo_1] = igPostsFull.post_img_list[1]
                table[post_vdo_2] = igPostsFull.post_img_list[2]
                table[post_vdo_3] = igPostsFull.post_img_list[3]
                table[post_vdo_4] = igPostsFull.post_img_list[4]
                table[post_vdo_5] = igPostsFull.post_img_list[5]
                table[post_vdo_6] = igPostsFull.post_img_list[6]
                table[post_vdo_7] = igPostsFull.post_img_list[7]
                table[post_vdo_8] = igPostsFull.post_img_list[8]
                table[post_vdo_9] = igPostsFull.post_img_list[9]
            }
        }

    }

    override fun to(row: ResultRow): IgPostsFull =
        IgPostsFull(
            _id = row[PostsTable._id],
            post_key = row[PostsTable.post_key],
            post_from = row[PostsTable.post_from],
            post_author = row[PostsTable.post_author],
            post_content = row[PostsTable.post_content],
            post_date = row[PostsTable.post_date],
            post_img_list = mutableListOf(
                row[PostsTable.post_img_0],
                row[PostsTable.post_img_1],
                row[PostsTable.post_img_2],
                row[PostsTable.post_img_3],
                row[PostsTable.post_img_4],
                row[PostsTable.post_img_5],
                row[PostsTable.post_img_6],
                row[PostsTable.post_img_7],
                row[PostsTable.post_img_8],
                row[PostsTable.post_img_9]
            ).filter { it != "" },
            post_vdo_list = mutableListOf(
                row[PostsTable.post_vdo_0],
                row[PostsTable.post_vdo_1],
                row[PostsTable.post_vdo_2],
                row[PostsTable.post_vdo_3],
                row[PostsTable.post_vdo_4],
                row[PostsTable.post_vdo_5],
                row[PostsTable.post_vdo_6],
                row[PostsTable.post_vdo_7],
                row[PostsTable.post_vdo_8],
                row[PostsTable.post_vdo_9]
            ).filter { it != "" }

        )
}