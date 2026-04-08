package com.example.calculatorapp

import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)
    }

    // Number Click
    fun onNumberClick(view: View) {
        val button = view as Button

        // Avoid leading zero issue
        if (currentInput == "0") {
            currentInput = button.text.toString()
        } else {
            currentInput += button.text
        }

        resultText.text = currentInput
    }

    // Decimal Click
    fun onDecimalClick(view: View) {
        if (!currentInput.contains(".")) {
            if (currentInput.isEmpty()) {
                currentInput = "0."
            } else {
                currentInput += "."
            }
            resultText.text = currentInput
        }
    }

    // Operator Click
    fun onOperatorClick(view: View) {
        val button = view as Button

        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toDouble()
            operator = button.text.toString()
            currentInput = ""
        }
    }

    // Equal Click
    fun onEqualClick(view: View) {

        if (currentInput.isEmpty() || operator.isEmpty()) return

        val secondNumber = currentInput.toDouble()
        val result: Double

        result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> {
                if (secondNumber == 0.0) {
                    resultText.text = "Error"
                    return
                }
                firstNumber / secondNumber
            }
            else -> 0.0
        }

        // Remove .0 if not needed
        val finalResult = if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }

        resultText.text = finalResult
        currentInput = finalResult
        operator = ""
    }

    // Clear
    fun onClearClick(view: View) {
        currentInput = ""
        operator = ""
        firstNumber = 0.0
        resultText.text = "0"
    }

    // Backspace
    fun onBackspaceClick(view: View) {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            resultText.text = if (currentInput.isEmpty()) "0" else currentInput
        }
    }
}