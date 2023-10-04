package com.josedev.mytodos.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.josedev.mytodos.components.Auth
import com.josedev.mytodos.ui.theme.MyTodosTheme

@Composable
fun MainScreen(activity: FragmentActivity) {
    val contextCompat: Context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Text(text = "Main Screen")
        Auth(activity)
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
//    MainScreen()
}