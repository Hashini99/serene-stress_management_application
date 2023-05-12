package com.example.serene.academic_goal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.serene.R
import com.example.serene.academic_goal.GoalMain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class GoalNotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val db = FirebaseFirestore.getInstance()

    override fun doWork(): Result {
        // Query all goals for the current user
        val query = db.collection("goal")
            .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
        val goals = query.get().getResult()?.toObjects(Goal::class.java)

        // Check due dates and send notifications for the ones that have arrived
        val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        val currentDate = Date()
        goals?.forEach { goal ->
            val dueDate = dateFormat.parse(goal.duedate)
            if (dueDate.time <= currentDate.time) {
                sendNotification(goal.title)
            }
        }

        return Result.success()
    }

    private fun sendNotification(title: String) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for Android 8.0 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "goal_notifications",
                "Goal Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }

        // Create a notification with a pending intent to open the goal list
        val intent = Intent(applicationContext, GoalMain::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(applicationContext, "goal_notifications")
            .setContentTitle("Goal Due")
            .setContentText("Your goal \"$title\" is due today.")
            .setSmallIcon(R.drawable.theme1)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        // Send the notification
        notificationManager.notify(0, notification)
    }
}