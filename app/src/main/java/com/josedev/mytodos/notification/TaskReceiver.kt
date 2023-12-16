package com.josedev.mytodos.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TaskReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        println("Was completed from notification")
    }
}