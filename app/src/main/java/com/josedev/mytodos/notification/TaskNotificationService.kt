package com.josedev.mytodos.notification

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.josedev.mytodos.MainActivity
import com.josedev.mytodos.R
import java.util.Calendar


class TaskNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(title: String, content: String) {
        val completeIntent = Intent(context, TaskReceiver::class.java)
        val notification = NotificationCompat.Builder(context, "random_id")
            .setSmallIcon(R.mipmap.ic_launcher_no_bg)
            .setContentTitle(title)
            .setContentText(content)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setAutoCancel(true)
            .addAction(
                R.mipmap.ic_launcher_no_bg,
                "Task Complete",
                PendingIntent.getBroadcast(
                    context,
                    0,
                    completeIntent,
                    PendingIntent.FLAG_IMMUTABLE))
            .build()
        notificationManager.notify(1, notification)
    }



}