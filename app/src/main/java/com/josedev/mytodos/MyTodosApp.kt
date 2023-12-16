package com.josedev.mytodos


import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyTodosApp: Application(){
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            "random_id",
            "random_name",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}