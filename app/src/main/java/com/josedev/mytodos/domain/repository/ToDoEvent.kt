package com.josedev.mytodos.domain.repository

import com.josedev.mytodos.domain.entity.Priority
import com.josedev.mytodos.domain.entity.ToDo

sealed interface ToDoEvent {
    object SaveToDo: ToDoEvent
    data class SetTitle(val title: String): ToDoEvent
    data class SetDescription(val description: String): ToDoEvent
    data class SetIsComplete(val isComplete: Boolean): ToDoEvent
    data class SetPriority(val priority: Priority): ToDoEvent
    object ShowDialog: ToDoEvent
    object HideDialog: ToDoEvent
    data class DeleteToDo(val todo: ToDo): ToDoEvent
}