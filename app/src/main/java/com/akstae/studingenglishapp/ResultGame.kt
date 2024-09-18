package com.akstae.studingenglishapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultGame : AppCompatActivity(){

    private lateinit var soundButtonHelper: SoundButtonHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_game)

        soundButtonHelper = SoundButtonHelper(this)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val retryButton: Button = findViewById(R.id.retryButton)

        val score = intent.getIntExtra("SCORE", 0)
        resultTextView.text = getString(R.string.words_guessed, score)

        soundButtonHelper.setSoundOnClickListener(retryButton, View.OnClickListener {
            val intent = Intent(this, GameLogic::class.java)
            startActivity(intent)
        })
    }
}