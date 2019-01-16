@file:Suppress("SpellCheckingInspection")

package dev.ryz3n.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.ryz3n.model.*
import dev.ryz3n.util.CrawlerTxtUtil
import dev.ryz3n.util.DateUtil.igPostDate2Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.NotNull
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.Exception

object DatabaseFactory {

    private val gson: Gson = Gson()

    fun init(@NotNull tableMap: MutableMap<String, BasePostsTable>) {
        Database.connect(dbConnect())
        try {
            tableMap.forEach { fileName, table ->
                transaction {
                    create(table)

                    val txt = CrawlerTxtUtil.readTxtFile("E:\\KTCODE\\instagram-crawler\\$fileName.txt")

                    val list = gson.fromJson<List<IgPostsFullByJson>>(
                        txt,
                        object : TypeToken<List<IgPostsFullByJson>>() {}.type
                    )

                    list.forEach { json ->

                        table.insert { it ->
                            it[post_key] = json.key
                            it[post_author] = json.comments[0].author
                            it[post_content] = json.comments[0].comment
                            it[post_from] = "instagram"
                            it[post_date] = igPostDate2Timestamp(json.datetime)

                            val imgList = arrayListOf("", "", "", "", "", "", "", "", "", "")
                            json.img_urls.forEachIndexed { urlIndex, url ->
                                imgList[urlIndex] = url
                            }
                            it[post_img_0] = imgList[0]
                            it[post_img_1] = imgList[1]
                            it[post_img_2] = imgList[2]
                            it[post_img_3] = imgList[3]
                            it[post_img_4] = imgList[4]
                            it[post_img_5] = imgList[5]
                            it[post_img_6] = imgList[6]
                            it[post_img_7] = imgList[7]
                            it[post_img_8] = imgList[8]
                            it[post_img_9] = imgList[9]

                            val vdoList = arrayListOf("", "", "", "", "", "", "", "", "", "")
                            json.vdo_urls.forEachIndexed { vdoIndex, url ->
                                vdoList[vdoIndex] = url
                            }
                            it[post_vdo_0] = vdoList[0]
                            it[post_vdo_1] = vdoList[1]
                            it[post_vdo_2] = vdoList[2]
                            it[post_vdo_3] = vdoList[3]
                            it[post_vdo_4] = vdoList[4]
                            it[post_vdo_5] = vdoList[5]
                            it[post_vdo_6] = vdoList[6]
                            it[post_vdo_7] = vdoList[7]
                            it[post_vdo_8] = vdoList[8]
                            it[post_vdo_9] = vdoList[9]
                        }
                    }
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
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


