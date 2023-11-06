package com.josedev.mytodos.navigation

sealed class AppScreens (val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object AuthScreen: AppScreens("auth_screen")
    object MainScreen: AppScreens("main_screen")
}
