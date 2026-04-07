package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)
    }

    fun onNumberClick(view: View) {
        val button = view as Button
        currentInput += button.text
        resultText.text = currentInput
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        firstNumber = currentInput.toDouble()
        operator = button.text.toString()
        currentInput = ""
    }

    fun onEqualClick(view: View) {
        val secondNumber = currentInput.toDouble()
        var result = 0.0

        when (operator) {
            "+" -> result = firstNumber + secondNumber
            "-" -> result = firstNumber - secondNumber
            "*" -> result = firstNumber * secondNumber
            "/" -> result = firstNumber / secondNumber
        }

        resultText.text = result.toString()
        currentInput = result.toString()
    }

    fun onClearClick(view: View) {
        currentInput = ""
        operator = ""
        firstNumber = 0.0
        resultText.text = "0"
    }
}