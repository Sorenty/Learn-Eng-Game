package com.akstae.studingenglishapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akstae.studingenglishapp.RegistrationActivity

import android.content.Intent

import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Создание вертикального лейаута
        val verticalLayout = LinearLayout(this)
        verticalLayout.orientation = LinearLayout.VERTICAL
        verticalLayout.gravity = Gravity.CENTER
        verticalLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        // Создание текстового поля
        val textView = TextView(this)
        textView.text = "Easy English for you"
        textView.textSize = 24f
        textView.gravity = Gravity.CENTER
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Создание изображения
        val imageView = ImageView(this)
        // Установка изображения (замените R.drawable.your_image на ваш ресурс)
        imageView.setImageResource(R.drawable.starticon)
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Создание кнопки
        val button = Button(this)
        button.text = "Начать"
        button.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Добавление элементов на вертикальный лейаут
        verticalLayout.addView(textView)
        verticalLayout.addView(imageView)
        verticalLayout.addView(button)

        // Установка обработчика нажатия на кнопку "Начать"
        button.setOnClickListener {
            // Создание Intent для перехода на RegistrationActivity
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        // Установка вертикального лейаута в качестве контента активити
        setContentView(verticalLayout)

    }
}