package com.josedev.mytodos.domain.entity

import java.time.LocalDate
import java.time.LocalTime

data class ToDoState(
    val todos: List<ToDo> = emptyList(),
    val title: String = "",
    val description: String = "",
    val taskDate: LocalDate = LocalDate.now(),
    val taskTime: LocalTime = LocalTime.now(),
//    val isComplete: Boolean = false,
//    val priority: Priority = Priority.LOW,
    val todoId: String = "",
    val isAddingTodo: Boolean = false // A flag to know whether or not show dialog
)
