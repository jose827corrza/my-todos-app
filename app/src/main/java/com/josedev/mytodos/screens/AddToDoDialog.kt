package com.josedev.mytodos.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.josedev.mytodos.domain.repository.ToDoEvent
import com.josedev.mytodos.domain.entity.ToDoState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state: ToDoState,
    onEvent: (ToDoEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ToDoEvent.HideDialog)
        },
        confirmButton = {
            Button(onClick = {
                onEvent(ToDoEvent.SaveToDo)
            }) {
                Text(text = "Create ToDo")
            }

        },
        title = {
            Text(text = "Add ToDo")
        },
        text = {
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.title,
                    onValueChange = {
                        onEvent(ToDoEvent.SetTitle(it))
                    },
                    placeholder = {
                        Text(
                            text = "Title"
                        )
                    })

                TextField(
                    value = state.description,
                    onValueChange = {
                        onEvent(ToDoEvent.SetDescription(it))
                    },
                    placeholder = {
                        Text(
                            text = "Description"
                        )
                    })

//                TextField(
//                    value = state.phoneNumber,
//                    onValueChange = {
//                        onEvent(ToDoEvent.SetPhoneNumber(it))
//                    },
//                    placeholder = {
//                        Text(
//                            text = "Phone Number"
//                        )
//                    })
            }
        }
    )
}