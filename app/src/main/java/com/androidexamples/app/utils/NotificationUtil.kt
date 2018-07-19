package com.androidexamples.app.utils

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log

/**
 * Created by marcos on 20/01/2017.
 */
object NotificationUtil {

    private val TAG = "NotificationUtil"

    fun create(context: Context, id: Int, intent: Intent, smallIcon: Int, contentTitle: String, contentText: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Intent para disparar o broadcast
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Cria a notification
        val builder = NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setAutoCancel(true)

        // Dispara a notification
        val notification = builder.build()
        notificationManager.notify(id, notification)

        Log.d(TAG, "Notification criada com sucesso")
    }

    fun createStackNotification(context: Context, id: Int, groupId: String, intent: Intent?, smallIcon: Int, contentTitle: String, contentText: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Intent para disparar o broadcast
        val p = if (intent != null) PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT) else null

        // Cria a notification
        val builder = NotificationCompat.Builder(context)
                .setContentIntent(p)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setGroup(groupId)
                .setAutoCancel(true)

        // Dispara a notification
        val n = builder.build()
        manager.notify(id, n)

        Log.d(TAG, "Notification criada com sucesso")
    }

    // Notificação simples sem abrir intent (usada para alertas, ex: no wear)
    fun create(context: Context, smallIcon: Int, contentTitle: String, contentText: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Cria a notification
        val builder = NotificationCompat.Builder(context)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setAutoCancel(true)

        // Dispara a notification
        val n = builder.build()
        manager.notify(0, n)

        Log.d(TAG, "Notification criada com sucesso")
    }

    fun cancell(context: Context, id: Int) {
        val nm = NotificationManagerCompat.from(context)
        nm.cancel(id)
    }

    fun cancellAll(context: Context) {
        val nm = NotificationManagerCompat.from(context)
        nm.cancelAll()
    }
}
