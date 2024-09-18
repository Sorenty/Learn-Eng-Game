package com.akstae.studingenglishapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.view.View

class ChooseAction : BaseActivity() {

    private lateinit var soundButtonHelper: SoundButtonHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.type_of_game)

        soundButtonHelper = SoundButtonHelper(this)

        val marafonButton: Button = findViewById(R.id.marafonGameButton)
        val nonstopButton: Button = findViewById(R.id.nonstopGameButton)
        val backButton: Button = findViewById(R.id.backButton)

        soundButtonHelper.setSoundOnClickListener(marafonButton, View.OnClickListener {
            val intent = Intent(this, MarafonGame::class.java)
            startActivity(intent)
        })

        soundButtonHelper.setSoundOnClickListener(nonstopButton, View.OnClickListener {
            val intent = Intent(this, GameLogic::class.java)
            startActivity(intent)
        })

        soundButtonHelper.setSoundOnClickListener(backButton, View.OnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        soundButtonHelper.release()
    }
}
