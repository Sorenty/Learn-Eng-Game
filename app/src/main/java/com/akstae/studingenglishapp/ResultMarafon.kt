package com.akstae.studingenglishapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultMarafon : BaseActivity(){

    private lateinit var soundButtonHelper: SoundButtonHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marafon_game)

        soundButtonHelper = SoundButtonHelper(this)
        val rightTextView: TextView = findViewById(R.id.rightTextView)
        val wrongTextView: TextView = findViewById(R.id.wrongTextView)
        val retryButton: Button = findViewById(R.id.retryButton)

        val scoreRight = intent.getIntExtra("SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 10)
        rightTextView.text = getString(R.string.words_right, scoreRight)
        wrongTextView.text = getString(R.string.words_wrong, totalQuestions-scoreRight)

        soundButtonHelper.setSoundOnClickListener(retryButton, View.OnClickListener {
            val intent = Intent(this, MarafonGame::class.java)
            startActivity(intent)
        })
    }
}