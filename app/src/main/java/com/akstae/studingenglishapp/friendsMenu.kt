package com.akstae.studingenglishapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class friendsMenu : AppCompatActivity() {

    private lateinit var soundButtonHelper: SoundButtonHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_list)

        soundButtonHelper = SoundButtonHelper(this)

        val backButton: Button = findViewById(R.id.backButton)
        soundButtonHelper.setSoundOnClickListener(backButton, View.OnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        })
    }
}