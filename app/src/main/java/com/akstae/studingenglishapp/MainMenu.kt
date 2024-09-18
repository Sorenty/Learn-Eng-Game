package com.akstae.studingenglishapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.content.SharedPreferences
import android.view.View

class MainMenu : BaseActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var soundButtonHelper: SoundButtonHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)
        soundButtonHelper = SoundButtonHelper(this)

        val settingsButton: Button = findViewById(R.id.settingsButton)
        val friendsButton: Button = findViewById(R.id.friendsButton)
        val startGameButton: Button = findViewById(R.id.startGameButton)

        soundButtonHelper.setSoundOnClickListener(startGameButton, View.OnClickListener {
            val intent = Intent(this, ChooseAction::class.java)
            startActivity(intent)
        })

        soundButtonHelper.setSoundOnClickListener(settingsButton, View.OnClickListener {
            val intent = Intent(this, SettingsPageMenu::class.java)
            startActivity(intent)
        })

        soundButtonHelper.setSoundOnClickListener(friendsButton, View.OnClickListener {
            val intent = Intent(this, friendsMenu::class.java)
            startActivity(intent)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        soundButtonHelper.release()
    }
}
