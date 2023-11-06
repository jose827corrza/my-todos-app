package com.josedev.mytodos.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josedev.mytodos.domain.entity.ToDo
import com.josedev.mytodos.domain.repository.ToDoDao

@Database(
    entities = [ToDo::class],
    version = 1
)
abstract class ToDoDatabase: RoomDatabase() {

    abstract val todoDao: ToDoDao
}