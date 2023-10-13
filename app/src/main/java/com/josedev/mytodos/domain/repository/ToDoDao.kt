package com.josedev.mytodos.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.josedev.mytodos.domain.entity.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Upsert
    suspend fun upsertToDo(todo: ToDo)

    @Delete
    suspend fun deleteToDo(todo: ToDo)

    @Query("SELECT * FROM todos")
    fun getAllToDos(): Flow<List<ToDo>>

    @Query("SELECT * FROM todos GROUP BY priority")
    fun groupByPriority(): Flow<List<ToDo>>
}