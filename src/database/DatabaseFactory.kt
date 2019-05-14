@file:Suppress("SpellCheckingInspection")

package dev.ryz3n.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.ryz3n.model.*
import dev.ryz3n.util.CrawlerTxtUtil
import dev.ryz3n.util.DateUtil.igPostDate2Timestamp
import dev.ryz3n.util.getMusicName
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

    fun init(@NotNull crawList: MutableList<String>) {
        Database.connect(dbConnect())
        transaction {
            create(BasePostsTable())
            create(BaseAuthorTable())
        }
        update(crawList)
    }

    fun update(@NotNull crawList: MutableList<String>) {

        crawList.forEach { fileName ->
            transaction {
                val txt = CrawlerTxtUtil.readTxtFile("E:\\KTCODE\\instagram-crawler\\$fileName-posts.txt")
                val postList = gson.fromJson<List<IgPostsFullByJson>>(
                    txt,
                    object : TypeToken<List<IgPostsFullByJson>>() {}.type
                )

                val profileTxt = CrawlerTxtUtil.readTxtFile("E:\\KTCODE\\instagram-crawler\\$fileName-profile.txt")
                val profile = gson.fromJson<IgAuthorByJson>(
                    profileTxt,
                    object : TypeToken<IgAuthorByJson>() {}.type
                )

                postList.forEach { post ->
                    try {
                        BasePostsTable().insert { postTable ->
                            postTable[post_key] = post.key
                            postTable[post_author] = post.comments[0].author
                            postTable[post_content] = post.comments[0].comment
                            postTable[post_date] = igPostDate2Timestamp(post.datetime)
                            postTable[post_music] =
                                    post.comments[0].comment.getMusicName(records = post.comments[0].author)

                            val imgList = arrayListOf("", "", "", "", "", "", "", "", "", "")
                            post.img_urls.forEachIndexed { urlIndex, url ->
                                imgList[urlIndex] = url
                            }
                            postTable[post_img_0] = imgList[0]
                            postTable[post_img_1] = imgList[1]
                            postTable[post_img_2] = imgList[2]
                            postTable[post_img_3] = imgList[3]
                            postTable[post_img_4] = imgList[4]
                            postTable[post_img_5] = imgList[5]
                            postTable[post_img_6] = imgList[6]
                            postTable[post_img_7] = imgList[7]
                            postTable[post_img_8] = imgList[8]
                            postTable[post_img_9] = imgList[9]

                            val vdoList = arrayListOf("", "", "", "", "", "", "", "", "", "")
                            if (!post.vdo_urls.isEmpty()){
                                post.vdo_urls.forEachIndexed { vdoIndex, url ->
                                    vdoList[vdoIndex] = url
                                }
                            }
                            postTable[post_vdo_0] = vdoList[0]
                            postTable[post_vdo_1] = vdoList[1]
                            postTable[post_vdo_2] = vdoList[2]
                            postTable[post_vdo_3] = vdoList[3]
                            postTable[post_vdo_4] = vdoList[4]
                            postTable[post_vdo_5] = vdoList[5]
                            postTable[post_vdo_6] = vdoList[6]
                            postTable[post_vdo_7] = vdoList[7]
                            postTable[post_vdo_8] = vdoList[8]
                            postTable[post_vdo_9] = vdoList[9]
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                try {
                    BaseAuthorTable().insert { profileTable ->
                        profileTable[author_ig_name] = fileName
                        profileTable[author_name] = profile.name
                        profileTable[author_info] = profile.desc
                        profileTable[author_avatar] = profile.photo_url
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
        hikariConfig.maximumPoolSize = 8
        hikariConfig.isAutoCommit = false
        hikariConfig.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        hikariConfig.validate()
        return HikariDataSource(hikariConfig)
    }
}


