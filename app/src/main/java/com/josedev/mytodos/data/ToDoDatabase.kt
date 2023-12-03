package com.josedev.mytodos.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.josedev.mytodos.domain.entity.ToDo
import com.josedev.mytodos.domain.repository.ToDoDao

@Database(
    entities = [ToDo::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class ToDoDatabase: RoomDatabase() {

    abstract val todoDao: ToDoDao
}