package com.josedev.mytodos.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josedev.mytodos.domain.entity.ToDoState
import com.josedev.mytodos.domain.repository.ToDoEvent
import com.josedev.mytodos.presentation.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(state: ToDoState, onEvent: (ToDoEvent) -> Unit) {
    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ToDoEvent.ShowDialog)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add task")
            }
        }
    ){ padding ->

        if(state.isAddingTodo){
            AddContactDialog(state = state, onEvent = onEvent)
        }


        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ){
            items(state.todos){todo ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Column (
                        modifier = Modifier.weight(1f)
                    ){
                        Text(
                            text = todo.title,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = todo.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                    IconButton(onClick = { onEvent(ToDoEvent.DeleteToDo(todo))}) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Todo complete")
                    }

                }
            }
        }
    }
}
