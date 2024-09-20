package com.example.labtwo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Установка слушателя для обработки отступов
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализация компонентов
        val ms: TextInputEditText = findViewById(R.id.Input)
        val s1: TextView = findViewById(R.id.SumView)
        val s2: TextView = findViewById(R.id.LTermView)
        val s3: TextView = findViewById(R.id.CountView)
        val calculateButton: Button = findViewById(R.id.button) // Добавьте кнопку в ваш XML

        // Установка обработчика нажатия кнопки
        calculateButton.setOnClickListener{
            // Получение текста из TextInputEditText
            val inputString: String = ms.text.toString()
            val mi: Double? = inputString.toDoubleOrNull() // Введенное число

            // Переменные для вычисления суммы
            var sum = 0.0 // сумма
            var lastTerm = 0.0 // последнее слагаемое
            var count = 0 // количество итераций
            var n = 1 // Начинаем с 1

            // Проверяем, что mi не равно null
            if (mi != null)
            {
                while (true)
                {
                    lastTerm = 1.0 / factorial(n) // Вычисляем 1/n!
                    if (lastTerm < mi)
                    {
                        break // Прекращаем, если 1/n! меньше mi
                    }
                    sum += lastTerm // Добавляем к сумме
                    count++ // Увеличиваем количество итераций
                    n += 2 // Переходим к следующему нечетному числу
                }
            }
            // Обновляем текстовые блоки
            s1.text = sum.toString()
            s2.text = lastTerm.toString() // Отображаем последнее слагаемое
            s3.text = count.toString()
        }
    }

    // Функция для вычисления факториала
    private fun factorial(n: Int): Long {
        require(n >= 0) { "n должно быть неотрицательным" }
        var result = 1L
        for (i in 1..n) {
            result *= i
        }
        return result
    }
}