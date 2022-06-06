package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var channel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "com.example.notification"
    private val desc = "Notifications"


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, SecondActivity::class.java)

        button.setOnClickListener {
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channel = NotificationChannel(channelId, desc, NotificationManager.IMPORTANCE_HIGH)

                channel.apply {
                    enableLights(true)
                    lightColor = Color.GRAY
                    enableVibration(false)
                }
                notificationManager.createNotificationChannel(channel)

                builder = Notification.Builder(this)
                    .setContentTitle("New notification")
                    .setContentText("Will open the Second Fragment")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setChannelId(channelId)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                    .setContentIntent(pendingIntent)
            }
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    builder = Notification.Builder(this)
                        .setContentTitle("New notification")
                        .setContentText("Will open the Second Fragment")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setChannelId(channelId)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                        .setContentIntent(pendingIntent)
                }
            }
            notificationManager.notify(4321, builder.build())
        }

    }
}