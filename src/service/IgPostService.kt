package dev.ryz3n.service

import dev.ryz3n.model.IgPostsFull
import dev.ryz3n.database.DatabaseFactory.dbQuery
import dev.ryz3n.model.BasePostsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class IgPostService(protected var table: BasePostsTable) : BaseService<IgPostsFull>() {

    override suspend fun get(): List<IgPostsFull> = dbQuery {
        table.selectAll().map { resultRow -> to(resultRow) }.sortedByDescending { it.post_date }
    }

    override suspend fun add(list: List<IgPostsFull>) = dbQuery {
        list.forEach { igPostsFull ->
            table.insert { it ->
                it[post_key] = igPostsFull.post_key
                it[post_from] = igPostsFull.post_from
                it[post_author] = igPostsFull.post_author
                it[post_content] = igPostsFull.post_content
                it[post_date] = igPostsFull.post_date

                it[post_img_0] = igPostsFull.post_img_list[0]
                it[post_img_1] = igPostsFull.post_img_list[1]
                it[post_img_2] = igPostsFull.post_img_list[2]
                it[post_img_3] = igPostsFull.post_img_list[3]
                it[post_img_4] = igPostsFull.post_img_list[4]
                it[post_img_5] = igPostsFull.post_img_list[5]
                it[post_img_6] = igPostsFull.post_img_list[6]
                it[post_img_7] = igPostsFull.post_img_list[7]
                it[post_img_8] = igPostsFull.post_img_list[8]
                it[post_img_9] = igPostsFull.post_img_list[9]

                it[post_vdo_0] = igPostsFull.post_img_list[0]
                it[post_vdo_1] = igPostsFull.post_img_list[1]
                it[post_vdo_2] = igPostsFull.post_img_list[2]
                it[post_vdo_3] = igPostsFull.post_img_list[3]
                it[post_vdo_4] = igPostsFull.post_img_list[4]
                it[post_vdo_5] = igPostsFull.post_img_list[5]
                it[post_vdo_6] = igPostsFull.post_img_list[6]
                it[post_vdo_7] = igPostsFull.post_img_list[7]
                it[post_vdo_8] = igPostsFull.post_img_list[8]
                it[post_vdo_9] = igPostsFull.post_img_list[9]
            }
        }

    }

    override fun to(row: ResultRow): IgPostsFull =
        IgPostsFull(
            _id = row[table._id],
            post_key = row[table.post_key],
            post_from = row[table.post_from],
            post_author = row[table.post_author],
            post_content = row[table.post_content],
            post_date = row[table.post_date],
            post_img_list = mutableListOf(
                row[table.post_img_0],
                row[table.post_img_1],
                row[table.post_img_2],
                row[table.post_img_3],
                row[table.post_img_4],
                row[table.post_img_5],
                row[table.post_img_6],
                row[table.post_img_7],
                row[table.post_img_8],
                row[table.post_img_9]
            ).filter { it != "" },
            post_vdo_list = mutableListOf(
                row[table.post_vdo_0],
                row[table.post_vdo_1],
                row[table.post_vdo_2],
                row[table.post_vdo_3],
                row[table.post_vdo_4],
                row[table.post_vdo_5],
                row[table.post_vdo_6],
                row[table.post_vdo_7],
                row[table.post_vdo_8],
                row[table.post_vdo_9]
            ).filter { it != "" }

        )
}