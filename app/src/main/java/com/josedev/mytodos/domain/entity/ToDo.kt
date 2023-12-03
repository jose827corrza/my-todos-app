package com.josedev.mytodos.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "todos")
data class ToDo(
    val title: String,
    val description: String,
    val taskDate: LocalDate,
    val taskTime: LocalTime,
//    val isComplete: Boolean,
//    val priority: Priority,
    val todoId: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
