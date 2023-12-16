package com.josedev.mytodos.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.josedev.mytodos.domain.entity.ToDoState
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar

class AlarmNotificationService(
    private val context: Context
): AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager


//    @SuppressLint("ScheduleExactAlarm")
//    fun schedule(day: LocalDate, time: LocalTime) {
//        val intent = Intent(context, AlarmReceiver::class.java).apply {
//            putExtra("TITLE", "testTitle")
//            putExtra("CONTENT", "testContent")
//        }
//
//        val calendar: Calendar = Calendar.getInstance().apply {
//            timeInMillis = System.currentTimeMillis()
//            set(Calendar.YEAR, day.year)
//            set(Calendar.DAY_OF_YEAR, day.dayOfYear)
//            set(Calendar.HOUR_OF_DAY, time.hour)
//            set(Calendar.MINUTE, time.minute)
//        }
//
//        println("Flag setting the alarm: ${LocalTime.now()}")
//
//        alarmManager.setExactAndAllowWhileIdle(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            PendingIntent.getBroadcast(
//                context,
//                day.hashCode(),
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT or  PendingIntent.FLAG_IMMUTABLE
//            )
//        )
//    }
//
//    fun cancel(day: LocalDate) {
//        alarmManager.cancel(
//            PendingIntent.getBroadcast(
//                context,
//                day.hashCode(),
//                Intent(context, AlarmReceiver::class.java),
//                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//            )
//        )
//    }

    @SuppressLint("ScheduleExactAlarm")
    override fun schedule(todo: ToDoState) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("TITLE", todo.title)
            putExtra("CONTENT", todo.description)
        }
println(todo.toString())
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.YEAR, todo.taskDate.year)
            set(Calendar.DAY_OF_YEAR, todo.taskDate.dayOfYear)
            set(Calendar.HOUR_OF_DAY, todo.taskTime.hour)
            set(Calendar.MINUTE, todo.taskTime.minute)
        }
    println("->: ${todo.taskTime}")
        println("Flag setting the alarm: ${LocalTime.now()}")

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            PendingIntent.getBroadcast(
                context,
                todo.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or  PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(todo: ToDoState) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                todo.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}