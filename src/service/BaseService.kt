package dev.ryz3n.service

import dev.ryz3n.database.DatabaseFactory.dbQuery
import dev.ryz3n.model.ChangeType
import dev.ryz3n.model.Notification
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll

abstract class BaseService<T> {

    protected val listeners = mutableMapOf<Int, suspend (Notification<T?>) -> Unit>()

    protected fun addChangeListener(id: Int, listener: suspend (Notification<T?>) -> Unit) {
        listeners[id] = listener
    }

    protected fun removeChangeListener(id: Int) = listeners.remove(id)


    abstract suspend fun get(): List<T>

    abstract suspend fun add(list: List<T>)

    abstract fun to(row: ResultRow): T
}