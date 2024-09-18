package com.akstae.studingenglishapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class RegistrationActivity : AppCompatActivity() {

    private lateinit var soundButtonHelper: SoundButtonHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_layout)

        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonAlreadyHaveAccount = findViewById<Button>(R.id.buttonAlreadyHaveAccount)
        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)

        soundButtonHelper = SoundButtonHelper(this)

        soundButtonHelper.setSoundOnClickListener(buttonAlreadyHaveAccount, View.OnClickListener {
            // Здесь можно добавить код для перехода на экран входа
        })

        soundButtonHelper.setSoundOnClickListener(buttonConfirm, View.OnClickListener {
            val username = editTextEmail.text.toString()
            val intent = Intent(this, MainMenu::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        soundButtonHelper.release()
    }
}
