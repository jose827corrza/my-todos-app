package com.josedev.mytodos.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.josedev.mytodos.auth.Biometric
import com.josedev.mytodos.navigation.AppScreens


@Composable
fun Auth(activity: FragmentActivity, navController: NavController) {

    val context = LocalContext.current

    // Using Compose  states are used
    var auth by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .background(if (auth) Color.Green else Color.Red)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hi there, auth to follow",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if(auth){
                auth = false
            }else{
                    // TODO
                Biometric.authenticate(
                    activity= activity,
                    title = "Biometric Auth",
                    subTitle = "Auth using your sensor",
                    description = "Only auth users are allowed to see the todos",
                    onSuccess = {
                                    auth = true
                                    navController.navigate(AppScreens.MainScreen.route)
                                },
                    onFailed = {

                            Toast.makeText(
                                context,
                                "Authentication failed",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                    })

            }
        }) {
            Text(if(!auth) "Authenticate" else "Close")
        }
    }
}