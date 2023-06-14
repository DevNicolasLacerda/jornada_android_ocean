package com.oceanbrasil.ocean_jornada_android_maio_2023.view.push_notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.oceanbrasil.ocean_jornada_android_maio_2023.view.push_notification.PushNotificationManager

class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        const val CHANNEL_ID = "DEFAULT"
    }

    override fun onNewToken(token: String) {
        Log.d("FIREBASE_MESSAGING", "New Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("FIREBASE_MESSAGING", "Message received: $message")

        val title = message.notification?.title
        val body = message.notification?.body

        if (title == null || body == null) {
            return
        }

        // Se quiser pegar os dados extras, usar: `message.data`

        PushNotificationManager.sendPushNotication(this, title, body)
    }
}
