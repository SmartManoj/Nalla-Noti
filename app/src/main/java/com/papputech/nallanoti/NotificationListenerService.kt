package com.papputech.nallanoti
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.widget.Toast

class MyNotificationListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val notification = sbn.notification
        val notificationText = notification.tickerText?.toString()

        // Check if the notification text contains the specific phrase
        if (notificationText != null && notificationText.contains("Message from")) {
            // Extract the number and the message
            val messageDetails = notificationText.substringAfter("Message from ")
            // demo wa.me/+919022690226
            if (messageDetails == "State Bank Of India") {
                Toast.makeText(applicationContext, "Scammer Detected - ${messageDetails}- Be Vigilante ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Implement other necessary methods like onNotificationRemoved if needed
}