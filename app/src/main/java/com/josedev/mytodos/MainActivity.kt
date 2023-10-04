package com.josedev.mytodos

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.josedev.mytodos.auth.AuthClass
import com.josedev.mytodos.navigation.AppNavigation
import com.josedev.mytodos.ui.theme.MyTodosTheme

// ComponentActivity() is replaced by the AppCompat to allow the use of biometric
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTodosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Auth() // Biometric
                    AppNavigation()
                }
            }
        }
        //Setup
        AuthClass().setupAuth(this, this@MainActivity)
//        setupAuth()
    }

//    private var canAuthenticate = false
//    // Because this var is defined in execution time, is used the lateinit
//    private lateinit var promptInfo: BiometricPrompt.PromptInfo
//    //methods
//    fun setupAuth() {
//        if(BiometricManager.from(this).canAuthenticate(
//                BiometricManager.Authenticators.BIOMETRIC_STRONG or
//                            BiometricManager.Authenticators.DEVICE_CREDENTIAL)
//            == BiometricManager.BIOMETRIC_SUCCESS)
//        {
//            canAuthenticate = true
//            promptInfo = BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Biometrical Auth")
//                .setSubtitle("Auth using your sensor")
//                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or
//                        BiometricManager.Authenticators.DEVICE_CREDENTIAL)
//                .build()
//        }
//    }
//
//    fun authenticate(auth: (auth:Boolean) -> Unit){
//        if(canAuthenticate){
//            BiometricPrompt(this, ContextCompat.getMainExecutor(this),
//                object : BiometricPrompt.AuthenticationCallback(){
//                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                        super.onAuthenticationSucceeded(result)
//                        auth(true)
//                    }
//                }).authenticate(promptInfo)
//        }else{
//            auth(true)
//        }
//    }
//
//
//    //Composables
//    @Composable
//    fun Auth() {
//        // Using Compose  states are used
//        var auth by remember {
//            mutableStateOf(false)
//        }
//
//        Column(
//            modifier = Modifier
//                .background(if (auth) Color.Green else Color.Red)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Hi there, auth to follow",
//                fontSize = 22.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Button(onClick = {
//                if(auth){
//                    auth = false
//                }else{
//                    authenticate {
//                        auth = it
//                    }
//                }
//            }) {
//                Text(if(!auth) "Authenticate" else "Close")
//            }
//        }
//    }


    @Preview(showSystemUi = true)
    @Composable
    fun GreetingPreview() {
        MyTodosTheme {
            // Auth()
        }
    }
}

