package com.josedev.mytodos.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.josedev.mytodos.domain.repository.ToDoEvent
import com.josedev.mytodos.domain.entity.ToDoState
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.chrono.ChronoLocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state: ToDoState,
    onEvent: (ToDoEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }
    // Will "run" everytime the states above changes
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Button(onClick = { dateDialogState.show() }) {
                        Text(text = "Pick Date")
                    }
                    Text(text = formattedDate)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { timeDialogState.show() }) {
                        Text(text = "Pick an Hour")
                    }
                    Text(text = formattedTime)
                }
            }
        }
    )
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "OK"){

            }
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a Date",
//            colors = DatePickerDefaults.colors() // That's how to change colors
            allowedDateValidator =  {
                it.dayOfMonth % 2 == 0
            }
        ){
            pickedDate = it
            onEvent(ToDoEvent.SetDate(pickedDate))
        }
    }

    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "OK"){

            }
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick an Hour",
//            colors = DatePickerDefaults.colors() // That's how to change colors
//            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
        ){
            pickedTime = it
            onEvent(ToDoEvent.SetTime(pickedTime))
        }
    }
}

