package pl.mftau.mftau.core.presentation.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.activities.MainActivity

class PushNotificationService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
            val title = it.title
            val body = it.body

            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(FLAG_ACTIVITY_CLEAR_TOP)
            }
            val pendingIntent = PendingIntent.getActivity(
                this, 0, intent, FLAG_IMMUTABLE
            )

            val notification = NotificationCompat.Builder(this, FCM_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.logo_notification)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val fcmChannel = NotificationChannel(
                    FCM_CHANNEL_ID,
                    FCM_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
                manager.createNotificationChannel(fcmChannel)
            }

            manager.notify(1226, notification.build())
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("token", token)
    }

    companion object {
        const val FCM_CHANNEL_ID = "mf_tau_fcm_channel"
        const val FCM_CHANNEL_NAME = "MF Tau Channel"
    }
}