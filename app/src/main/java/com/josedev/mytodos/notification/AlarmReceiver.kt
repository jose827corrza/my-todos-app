package com.josedev.mytodos.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.time.LocalTime

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val taskNotificationService = TaskNotificationService(context)
        val title = intent?.getStringExtra("TITLE") ?: ""
        val content = intent?.getStringExtra("CONTENT") ?: ""
        println("Flag executing the alarm: ${LocalTime.now()}")
        taskNotificationService.showNotification(title, content)
    }
}