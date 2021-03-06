package com.example.lesson5

import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationService: FirebaseMessagingService() {

    companion object{
        const val POSTS_CHANNEL_ID = "posts"
        private var postNotificationId = 1
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    override fun onNewToken(token: String) {
        Log.d("PushToken", token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("Push", "Got message")
        message.notification?.let {
            val notification = NotificationCompat.Builder(this, POSTS_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_profile_image)
                .setContentTitle(it.title)
                .setContentText(it.body)
                .build()
            NotificationManagerCompat.from(this)
                .notify(postNotificationId++, notification)
        }
    }

    private fun createChannel() {
        val channel = NotificationChannelCompat.Builder(POSTS_CHANNEL_ID, NotificationManagerCompat.IMPORTANCE_HIGH)
            .setName("New posts")
            .build()
        NotificationManagerCompat.from(applicationContext)
            .createNotificationChannel(channel)
    }
}