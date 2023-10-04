package com.josedev.mytodos.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.josedev.mytodos.components.Auth

@Composable
fun AuthScreen(activity: FragmentActivity, navController: NavController) {
    val contextCompat: Context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Text(text = "Main Screen")
        Auth(activity, navController)
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
//    MainScreen()
}