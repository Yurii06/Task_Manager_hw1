package com.geektech.taskmanager.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.geektech.taskmanager.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class PushNotificationService: FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Heads Up Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = android.app.Notification.Builder(this, CHANNEL_ID)
        notification.setSmallIcon(R.mipmap.ic_launcher)
        notification.setContentTitle(message.notification?.title)
        notification.setContentTitle(message.notification?.body)
        notification.setAutoCancel(false)

        NotificationManagerCompat.from(this).notify(1, notification.build())
    }

    companion object{
        const val CHANNEL_ID = "HEADS_UP_NOTIFICATION"
    }
}