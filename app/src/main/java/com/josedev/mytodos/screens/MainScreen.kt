package com.josedev.mytodos.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.josedev.mytodos.domain.entity.ToDoState
import com.josedev.mytodos.domain.repository.ToDoEvent
import com.josedev.mytodos.presentation.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(state: ToDoState, onEvent: (ToDoEvent) -> Unit) {
    Scaffold (
        modifier = Modifier,
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
//            item {
//                BannerComposable()
//            }
            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green),
                    horizontalArrangement = Arrangement.Center,

                ){
                    Text(
                        text = "My Todos",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                }
                Divider(
                    color = Color.Black,
                    thickness = 3.dp
                )
            }
            items(state.todos){todo ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
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
                            text = todo.description,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                    IconButton(onClick = { onEvent(ToDoEvent.DeleteToDo(todo))}) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Todo complete")
                    }

                }
            }
        }
    }


}


@Composable
fun BannerComposable() {
    val adWidth = LocalConfiguration.current.screenWidthDp
    
    AndroidView(factory = {context ->
        val adView = AdView(context)
        adView.setAdSize(AdSize(adWidth, 50))
        adView.apply {
            adUnitId = "ca-app-pub-3940256099942544/6300978111"
            loadAd(AdRequest.Builder().build())
        }
    })
}
