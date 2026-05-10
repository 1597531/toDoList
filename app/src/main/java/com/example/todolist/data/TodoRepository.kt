package com.example.todolist.data

import com.example.todolist.data.local.TodoDao
import com.example.todolist.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val dao: TodoDao) {

    fun observeTodos(searchQuery: String): Flow<List<TodoEntity>> {
        val pattern = if (searchQuery.isBlank()) {
            "%"
        } else {
            "%${searchQuery.trim()}%"
        }
        return dao.observeTodos(pattern)
    }

    suspend fun getById(id: Long): TodoEntity? = dao.getById(id)

    suspend fun insert(title: String, detail: String?) {
        val now = System.currentTimeMillis()
        dao.insert(
            TodoEntity(
                id = 0,
                title = title.trim(),
                detail = detail?.trim()?.takeIf { it.isNotEmpty() },
                isCompleted = false,
                createdAtMillis = now,
                updatedAtMillis = now,
            ),
        )
    }

    suspend fun update(entity: TodoEntity, title: String, detail: String?) {
        dao.update(
            entity.copy(
                title = title.trim(),
                detail = detail?.trim()?.takeIf { it.isNotEmpty() },
                updatedAtMillis = System.currentTimeMillis(),
            ),
        )
    }

    suspend fun toggleComplete(entity: TodoEntity) {
        dao.update(
            entity.copy(
                isCompleted = !entity.isCompleted,
                updatedAtMillis = System.currentTimeMillis(),
            ),
        )
    }

    suspend fun delete(entity: TodoEntity) {
        dao.delete(entity)
    }
}
