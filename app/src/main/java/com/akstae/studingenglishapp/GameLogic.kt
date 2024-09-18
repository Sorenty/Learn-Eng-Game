package com.akstae.studingenglishapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameLogic : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var backButton: Button

    private lateinit var soundButtonHelper: SoundButtonHelper

    private val words = mapOf(
        "Человек" to "Human",
        "Рыба" to "Fish",
        "Машина" to "Car",
        "Телефон" to "Phone",
        "Кошка" to "Cat",
        "Собака" to "Dog",
        "Дом" to "House",
        "Книга" to "Book",
        "Стол" to "Table",
        "Стул" to "Chair",
        "Дерево" to "Tree",
        "Цветок" to "Flower",
        "Птица" to "Bird",
        "Ручка" to "Pen",
        "Карандаш" to "Pencil",
        "Змея" to "Snake",
        "Лошадь" to "Horse",
        "Корова" to "Cow",
        "Молоко" to "Milk",
        "Хлеб" to "Bread",
        "Яблоко" to "Apple",
        "Банан" to "Banana",
        "Апельсин" to "Orange",
        "Виноград" to "Grape",
        "Вода" to "Water",
        "Огонь" to "Fire",
        "Земля" to "Earth",
        "Воздух" to "Air",
        "Река" to "River",
        "Море" to "Sea",
        "Океан" to "Ocean",
        "Гора" to "Mountain",
        "Долина" to "Valley",
        "Лес" to "Forest",
        "Пустыня" to "Desert",
        "Остров" to "Island",
        "Небо" to "Sky",
        "Звезда" to "Star",
        "Луна" to "Moon",
        "Солнце" to "Sun",
        "Дождь" to "Rain",
        "Снег" to "Snow",
        "Ветер" to "Wind",
        "Гроза" to "Thunderstorm",
        "Облако" to "Cloud",
        "Туман" to "Fog",
        "Свет" to "Light",
        "Тьма" to "Darkness",
        "Завтрак" to "Breakfast",
        "Обед" to "Lunch",
        "Ужин" to "Dinner",
        "Суп" to "Soup",
        "Мясо" to "Meat",
        "Рыба" to "Fish",
        "Яйцо" to "Egg",
        "Сыр" to "Cheese",
        "Салат" to "Salad",
        "Сахар" to "Sugar",
        "Соль" to "Salt",
        "Перец" to "Pepper",
        "Масло" to "Butter",
        "Оливковое масло" to "Olive oil",
        "Картофель" to "Potato",
        "Морковь" to "Carrot",
        "Помидор" to "Tomato",
        "Огурец" to "Cucumber",
        "Капуста" to "Cabbage",
        "Лук" to "Onion",
        "Чеснок" to "Garlic",
        "Спагетти" to "Spaghetti",
        "Пицца" to "Pizza",
        "Гамбургер" to "Hamburger",
        "Сэндвич" to "Sandwich",
        "Мороженое" to "Ice cream",
        "Торт" to "Cake",
        "Шоколад" to "Chocolate",
        "Конфета" to "Candy",
        "Печенье" to "Cookie",
        "Чай" to "Tea",
        "Кофе" to "Coffee",
        "Сок" to "Juice",
        "Молоко" to "Milk",
        "Коктейль" to "Cocktail",
        "Лимонад" to "Lemonade",
        "Вино" to "Wine",
        "Пиво" to "Beer",
        "Водка" to "Vodka",
        "Шампанское" to "Champagne",
        "Кровать" to "Bed",
        "Диван" to "Sofa",
        "Кресло" to "Armchair",
        "Шкаф" to "Wardrobe",
        "Полка" to "Shelf",
        "Зеркало" to "Mirror",
        "Ковёр" to "Carpet",
        "Занавеска" to "Curtain"
    )

    private var correctWord: String? = null
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_logic)

        questionTextView = findViewById(R.id.questionTextView)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        backButton = findViewById(R.id.backButton)

        soundButtonHelper = SoundButtonHelper(this)
        val backButton: Button = findViewById(R.id.backButton)
        soundButtonHelper.setSoundOnClickListener(backButton, View.OnClickListener {
            val intent = Intent(this, ChooseAction::class.java)
            startActivity(intent)
        })

        generateQuestion()

        val buttons = listOf(button1, button2, button3, button4)
        buttons.forEach { button ->
            soundButtonHelper.setSoundOnClickListener(button, View.OnClickListener {
                if (button.text == correctWord) {
                    score++
                    generateQuestion()
                } else {
                    val intent = Intent(this, ResultGame::class.java)
                    intent.putExtra("SCORE", score)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }

    private fun generateQuestion() {
        val randomWord = words.entries.random()
        questionTextView.text = randomWord.key
        correctWord = randomWord.value

        val options = words.values.shuffled().take(4).toMutableList()
        if (!options.contains(correctWord)) {
            options[Random.nextInt(4)] = correctWord!!
        }

        val buttons = listOf(button1, button2, button3, button4)
        buttons.zip(options.shuffled()).forEach { (button, word) ->
            button.text = word
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundButtonHelper.release()
    }
}
