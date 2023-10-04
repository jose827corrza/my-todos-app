package com.josedev.mytodos.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josedev.mytodos.task.TaskItem

@Composable
fun MainScreen() {
    Main()

}

@Composable
fun Main() {
    var items by remember {
        mutableStateOf(
            (1..20).map {
                TaskItem(
                    title = "Item $it",
                    description = "Some descrip $it",
                    isComplete = false
                )
            }
        )
    }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(items.size){ i ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        // TODO
                               items = items.mapIndexed{j, item ->
                                    if(i==j){
                                        item.copy(isComplete = !item.isComplete)
                                    }else item
                               }
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    Text(
                        text = items[i].title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp)
                    Text(text = items[i].description)
                }
                if(items[i].isComplete){
                    Icon(
                        imageVector = Icons.Default.Check,
                        tint = Color.Green,
                        contentDescription = "go to details",
                        modifier = Modifier.size(20.dp))
                }

            }
        }
    }
}