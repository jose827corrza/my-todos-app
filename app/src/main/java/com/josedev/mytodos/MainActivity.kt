package com.josedev.mytodos

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.josedev.mytodos.auth.Biometric
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
                    AppNavigation(this@MainActivity)
                }
            }
        }
    }
    @Preview(showSystemUi = true)
    @Composable
    fun GreetingPreview() {
        MyTodosTheme {
            // Auth()
        }
    }
}

