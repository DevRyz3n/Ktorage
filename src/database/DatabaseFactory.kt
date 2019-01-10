@file:Suppress("SpellCheckingInspection")

package dev.ryz3n.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.ryz3n.model.IgPostsFullByJson
import dev.ryz3n.model.PostsTable
import dev.ryz3n.util.CrawlerTxtUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.Exception

object DatabaseFactory {

    fun init(gson: Gson = Gson()) {
        Database.connect(dbConnect())
        transaction {
            create(PostsTable)
            val txt = CrawlerTxtUtil.readTxtFile("E:\\KTCODE\\instagram-crawler\\output.txt")

            val list =
                gson.fromJson<List<IgPostsFullByJson>>(txt, object : TypeToken<List<IgPostsFullByJson>>() {}.type)


            list.forEach { json ->
                try {
                    PostsTable.insert { table ->
                        table[post_key] = json.key
                        table[post_author] = "post_author"
                        table[post_content] = "post_content"
                        table[post_from] = "instagram"
                        table[post_date] = json.datetime

                        val imgList = arrayListOf("", "", "", "", "", "", "", "", "", "")
                        var index = 0
                        json.img_urls.forEach { url ->
                            imgList[index] = url
                            index++
                        }

                        table[post_img_0] = imgList[0]
                        table[post_img_1] = imgList[1]
                        table[post_img_2] = imgList[2]
                        table[post_img_3] = imgList[3]
                        table[post_img_4] = imgList[4]
                        table[post_img_5] = imgList[5]
                        table[post_img_6] = imgList[6]
                        table[post_img_7] = imgList[7]
                        table[post_img_8] = imgList[8]
                        table[post_img_9] = imgList[9]
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }
    }

    suspend fun <T> dbQuery(
        block: () -> T
    ): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }


    private fun dbConnect(): HikariDataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.driverClassName = "org.h2.Driver"
        hikariConfig.jdbcUrl = "jdbc:h2:~/test"
        hikariConfig.username = "admin"
        hikariConfig.password = "1234"
        hikariConfig.maximumPoolSize = 3
        hikariConfig.isAutoCommit = false
        hikariConfig.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        hikariConfig.validate()
        return HikariDataSource(hikariConfig)
    }
}


