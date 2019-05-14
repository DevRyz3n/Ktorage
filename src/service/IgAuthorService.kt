package dev.ryz3n.service

import dev.ryz3n.model.BaseAuthorTable
import dev.ryz3n.model.IgAuthor
import org.jetbrains.exposed.sql.ResultRow
import dev.ryz3n.database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class IgAuthorService(private var table: BaseAuthorTable) : BaseService<IgAuthor>() {
    override suspend fun getAll(): List<IgAuthor> = dbQuery {
        table.selectAll().map { resultRow -> to(resultRow) }
    }

    override suspend fun get(author: String) = dbQuery{
        table.select { table.author_ig_name eq author }.map { resultRow -> to(resultRow) }
    }


    override suspend fun add(list: List<IgAuthor>) {}

    override fun to(row: ResultRow): IgAuthor = IgAuthor(
        _id = row[table._id],
        author_name = row[table.author_name],
        author_ig_name = row[table.author_ig_name],
        author_avatar = row[table.author_avatar],
        author_info = row[table.author_info]
    )
}