package com.akstae.studingenglishapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    private lateinit var musicReceiver: MusicReceiver // Теперь MusicReceiver является внутренним классом
    // Внутренний класс MusicReceiver
    inner class MusicReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val musicEnabled = intent?.getBooleanExtra("musicEnabled", true) ?: true
            if (musicEnabled) {
                startService(Intent(this@BaseActivity, MusicSettings::class.java))
            } else {
                stopService(Intent(this@BaseActivity, MusicSettings::class.java))
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        musicReceiver = MusicReceiver() // Создаем экземпляр MusicReceiver

        val filter = IntentFilter("com.akstae.studingenglishapp.MUSIC_CONTROL")
        registerReceiver(musicReceiver, filter, RECEIVER_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(musicReceiver)
    }
}
