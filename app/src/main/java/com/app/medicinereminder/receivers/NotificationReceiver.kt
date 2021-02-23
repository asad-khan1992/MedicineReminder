package com.app.medicinereminder.receivers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.app.medicinereminder.R
import com.app.medicinereminder.helpers.GeneralHelper

class NotificationReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
    }

    private fun showNotification(context: Context) {
        val contentView = RemoteViews(context.packageName, R.layout.layout_custom_notification)
        contentView.setTextViewText(R.id.tv_time,GeneralHelper.getTimeFromMilliSeconds(System.currentTimeMillis(),"HH:mm"))
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, "MedicalReminder")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("title")
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setCustomContentView(contentView)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(context)){
            notify(0,notificationBuilder.build())
        }
    }
}