package com.josedev.mytodos.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import kotlinx.coroutines.delay


@Composable
fun Auth(activity: FragmentActivity, navController: NavController) {

    val context = LocalContext.current

    // Using Compose  states are used
    var auth by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxHeight()

    ){

        Column(
            modifier = Modifier
    //            .background(if (auth) Color.Green else Color.White)
                .background(Color.White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if(!auth)"Hi there, auth to follow" else "Welcome Back",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            if(!auth){
                Button(onClick = {
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
                                "Authentication failed, be sure you have access",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        },
                        onError = {
                            Toast.makeText(
                                context,
                                "Authentication Error, you might want to enable Pin Lock and Fingerprint",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            navController.navigate(AppScreens.MainScreen.route)
                        })


                }) {
    //            Text(if(!auth) "Authenticate" else "Close")
                    Text("Authenticate")
                }
            }
    //        Spacer(modifier = Modifier.height(8.dp))
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "You can protect your ToDos by enabling either the PinLock or FingerPrint in your device",
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                modifier = Modifier.padding(30.dp)
            )
        }
    }
}