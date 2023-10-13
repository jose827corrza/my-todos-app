package com.josedev.mytodos.domain.entity

data class ToDoState(
    val todos: List<ToDo> = emptyList(),
    val title: String = "",
    val description: String = "",
    val isComplete: Boolean = false,
    val priority: Priority = Priority.LOW,
    val todoId: String = "",
    val isAddingTodo: Boolean = false // A flag to know whether or not show dialog
)
