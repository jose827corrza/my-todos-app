package com.josedev.mytodos.notification

import com.josedev.mytodos.domain.entity.ToDoState

interface AlarmScheduler {

    fun schedule(todo: ToDoState)
    fun cancel(todo: ToDoState)
}