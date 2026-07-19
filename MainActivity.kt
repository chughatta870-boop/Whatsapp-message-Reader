package com.mijaz.whatsappreader

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.mijaz.whatsappreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.statusText.text =
            "Ye app WhatsApp message aane par usay bulund awaz se parh kar sunayegi.\n\n" +
            "Shuru karne ke liye 'Notification Access On Karein' button dabayein, " +
            "aur agli screen par 'WhatsApp Awaz Reader' ko ON kar dein."

        binding.enableButton.setOnClickListener {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }
    }
}
