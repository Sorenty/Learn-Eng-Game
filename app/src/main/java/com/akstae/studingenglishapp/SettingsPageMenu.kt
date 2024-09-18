package com.akstae.studingenglishapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class SettingsPageMenu : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var musicSwitch: Switch
    private lateinit var soundSwitch: Switch
    private lateinit var soundButtonHelper: SoundButtonHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_menu)

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)
        soundButtonHelper = SoundButtonHelper(this)

        // Инициализация переключателей
        musicSwitch = findViewById(R.id.musicSwitch)
        soundSwitch = findViewById(R.id.soundSwitch)

        // Проверка первого запуска и сброс настроек
        if (isFirstRun()) {
            resetSettings()
        }

        // Загрузка сохраненных настроек
        loadSettings()

        // Обработка изменения состояния переключателей
        musicSwitch.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPreferences.edit()
            editor.putBoolean("music", isChecked)
            editor.apply()

            // Отправка широковещательного сообщения
            val intent = Intent("com.akstae.studingenglishapp.MUSIC_CONTROL")
            intent.putExtra("musicEnabled", isChecked)
            sendBroadcast(intent)
        }

        soundSwitch.isChecked = sharedPreferences.getBoolean("sound_enabled", true)

        soundSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Сохранить новое состояние в SharedPreferences
            with(sharedPreferences.edit()) {
                putBoolean("sound_enabled", isChecked)
                apply()
            }
        }

        val backButton: Button = findViewById(R.id.backButton)
        soundButtonHelper.setSoundOnClickListener(backButton, View.OnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        })
    }

    private fun isFirstRun(): Boolean {
        val firstRun = sharedPreferences.getBoolean("firstRun", true)
        if (firstRun) {
            sharedPreferences.edit().putBoolean("firstRun", false).apply()
        }
        return firstRun
    }

    private fun resetSettings() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("music", false)
        editor.putBoolean("sound_enabled", false)
        editor.apply()
    }

    private fun loadSettings() {
        val musicEnabled = sharedPreferences.getBoolean("music", false)
        val soundEnabled = sharedPreferences.getBoolean("sound_enabled", false)

        musicSwitch.isChecked = musicEnabled
        soundSwitch.isChecked = soundEnabled
    }

    private fun saveSettings(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }
}
