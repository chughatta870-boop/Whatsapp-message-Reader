package com.mijaz.whatsappreader

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.speech.tts.TextToSpeech
import java.util.Locale

class WhatsAppNotificationListener : NotificationListenerService(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var ttsReady = false

    // WhatsApp aur WhatsApp Business dono ke package names
    private val whatsappPackages = setOf(
        "com.whatsapp",
        "com.whatsapp.w4b"
    )

    override fun onCreate() {
        super.onCreate()
        tts = TextToSpeech(applicationContext, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Urdu locale try karo, na mile to system default use ho jayega
            val urduResult = tts?.setLanguage(Locale("ur", "PK"))
            if (urduResult == TextToSpeech.LANG_MISSING_DATA ||
                urduResult == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                tts?.setLanguage(Locale.getDefault())
            }
            ttsReady = true
        }
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (sbn.packageName !in whatsappPackages) return

        val extras = sbn.notification.extras
        val sender = extras.getCharSequence(Notification.EXTRA_TITLE)?.toString() ?: "Kisi ne"
        val message = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""

        // Group summary notifications (jinme actual text nahi hota) ko skip karo
        if (message.isBlank()) return

        val spokenText = "$sender ne message bheja: $message"
        speak(spokenText)
    }

    private fun speak(text: String) {
        if (ttsReady) {
            tts?.speak(text, TextToSpeech.QUEUE_ADD, null, System.currentTimeMillis().toString())
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}
