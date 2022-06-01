package com.example.lesson5

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.lesson5.domain.CreatePostUseCase
import com.example.lesson5.domain.UriToByteArrayUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PostCreateService : Service(), CoroutineScope by MainScope() {

    @Inject
    lateinit var createPostUseCase: CreatePostUseCase
    @Inject
    lateinit var uriToByteArrayUseCase: UriToByteArrayUseCase

    companion object {
        private const val ACTION_EXECUTE = "com.example.lesson5.action.EXECUTE"
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_PROGRESS = "progress"
        private const val EXTRA_TEXT = "com.example.lesson5.extras.TEXT"
        private const val EXTRA_IMAGES = "com.example.lesson5.extras.IMAGES"

        fun newIntent(context: Context, text: String?, images: List<Uri>?) = Intent(
            context, PostCreateService::class.java
        ).apply {
            action = ACTION_EXECUTE
            putExtra(EXTRA_TEXT, text)
            putExtra(EXTRA_IMAGES, images?.toTypedArray())
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_EXECUTE) {
            startForeground(NOTIFICATION_ID, createNotification())
            launch {
                val listOfByteArray = uriToByteArrayUseCase.invoke(
                    intent.getParcelableArrayExtra(EXTRA_IMAGES)?.map {
                        it as Uri
                    }?.toList()
                )
                createPostUseCase.invoke(intent.extras?.getString(EXTRA_TEXT), listOfByteArray)
                stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    private fun createNotification(): Notification {
        createChannel()
        return NotificationCompat.Builder(this, CHANNEL_PROGRESS)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Creating post...")
            .setProgress(0, 0, true)
            .build()
    }

    private fun createChannel() {
        val channel = NotificationChannelCompat.Builder(
            CHANNEL_PROGRESS,
            NotificationManagerCompat.IMPORTANCE_LOW
        )
            .setName("Data upload")
            .build()
        NotificationManagerCompat.from(this)
            .createNotificationChannel(channel)
    }
}