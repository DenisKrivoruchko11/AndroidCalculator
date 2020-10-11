package com.e.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.e.calculator.utils.ClickHandler

class MainActivity : AppCompatActivity() {

    private fun addClicks(buttons: Array<Int>, expressionField: TextView) {
        val handler = ClickHandler(expressionField)

        buttons.forEach { button ->
            findViewById<Button>(button).setOnClickListener {
                when (button) {
                    R.id.button0 -> handler.buttonDigitClick('0')
                    R.id.button1 -> handler.buttonDigitClick('1')
                    R.id.button2 -> handler.buttonDigitClick('2')
                    R.id.button3 -> handler.buttonDigitClick('3')
                    R.id.button4 -> handler.buttonDigitClick('4')
                    R.id.button5 -> handler.buttonDigitClick('5')
                    R.id.button6 -> handler.buttonDigitClick('6')
                    R.id.button7 -> handler.buttonDigitClick('7')
                    R.id.button8 -> handler.buttonDigitClick('8')
                    R.id.button9 -> handler.buttonDigitClick('9')

                    R.id.buttonPlus -> handler.buttonOperationClick('+')
                    R.id.buttonMinus -> handler.buttonOperationClick('-')
                    R.id.buttonMultiply -> handler.buttonOperationClick('*')
                    R.id.buttonDivide -> handler.buttonOperationClick('/')

                    R.id.buttonOpenBracket -> handler.buttonOpenBracketClick()
                    R.id.buttonCloseBracket -> handler.buttonCloseBracketClick()
                    R.id.buttonPoint -> handler.buttonPointClick()
                    R.id.buttonEqual -> handler.buttonEqualClick()
                    R.id.buttonC -> handler.buttonCClick()
                    R.id.buttonCE -> handler.buttonCEClick()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val expressionField = findViewById<TextView>(R.id.expressionField)
        val buttons = arrayOf(
            R.id.button1, R.id.button2, R.id.button3, R.id.buttonPlus,
            R.id.button4, R.id.button5, R.id.button6, R.id.buttonMinus,
            R.id.button7, R.id.button8, R.id.button9, R.id.buttonMultiply,
            R.id.buttonOpenBracket, R.id.button0, R.id.buttonCloseBracket, R.id.buttonDivide,
            R.id.buttonPoint, R.id.buttonEqual, R.id.buttonC, R.id.buttonCE
        )

        addClicks(buttons, expressionField)
    }
}