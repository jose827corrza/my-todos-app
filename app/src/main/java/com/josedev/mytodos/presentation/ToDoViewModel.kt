package com.josedev.mytodos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josedev.mytodos.domain.entity.Priority
import com.josedev.mytodos.domain.entity.ToDo
import com.josedev.mytodos.domain.entity.ToDoState
import com.josedev.mytodos.domain.repository.ToDoDao
import com.josedev.mytodos.domain.repository.ToDoEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val dao: ToDoDao
) : ViewModel() {
//    private val _priority = MutableStateFlow(Priority.HIGH)
    val _state = MutableStateFlow(ToDoState())
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _todos = _state.flatMapLatest {todoState ->
        dao.getAllToDos()

    }

    val state = combine(_state, _todos){state, todos ->
        state.copy(
            todos = todos,

        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ToDoState())

    fun OnEvent(event: ToDoEvent) {
        when(event){
            is ToDoEvent.DeleteToDo -> {
                viewModelScope.launch {
                    dao.deleteToDo(event.todo)
                }
            }
            ToDoEvent.HideDialog -> {
                // state updated
                _state.update {
                    it.copy(
                        isAddingTodo = false
                    )
                }
            }
            ToDoEvent.SaveToDo -> {
                val title = state.value.title
                val description = state.value.description
                val taskDate = state.value.taskDate
                val taskTime = state.value.taskTime
                val ranUUID = UUID.randomUUID()

                if(title.isBlank()){
                    return
                }
                val todo = ToDo(
                    title = title,
                    description = description,
//                    isComplete = false,
//                    priority = state.value.priority,
                    todoId = ranUUID.toString(),
                    taskDate = taskDate,
                    taskTime = taskTime,
                )

                viewModelScope.launch {
                    dao.upsertToDo(todo)
                }
                // state updated
                _state.update {
                    it.copy(
                        isAddingTodo = false,
                        title = "",
                        description = "",
                        todoId = "",

                    )
                }
            }
            is ToDoEvent.SetDescription -> {
                // state updated
                _state.update {
                    it.copy(
                        description = event.description
                    )
                }
            }
//            is ToDoEvent.SetIsComplete -> {
//                _state.update {
//                    it.copy(
//                        isComplete = event.isComplete
//                    )
//                }
//            }
//            is ToDoEvent.SetPriority -> {
//                _state.update {
//                    it.copy(
//                        priority = event.priority
//                    )
//                }
//            }
            is ToDoEvent.SetTitle -> {
                // state updated
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }
            ToDoEvent.ShowDialog -> {
                // state updated
                _state.update {
                    it.copy(
                        isAddingTodo = true
                    )
                }
            }
        }
    }
}