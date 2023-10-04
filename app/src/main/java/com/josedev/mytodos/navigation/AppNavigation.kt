package com.josedev.mytodos.navigation

import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josedev.mytodos.screens.AuthScreen
import com.josedev.mytodos.screens.MainScreen
import com.josedev.mytodos.screens.SplashScreen

@Composable
fun AppNavigation(activity: FragmentActivity) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route,
            ){
            composable(AppScreens.SplashScreen.route){
                SplashScreen(navController)
            }
            composable(AppScreens.AuthScreen.route){
                AuthScreen(activity, navController)
            }
            composable(AppScreens.MainScreen.route){
                MainScreen()
            }
    }
}