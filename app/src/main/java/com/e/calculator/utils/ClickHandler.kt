package com.e.calculator.utils

import android.widget.TextView
import java.lang.Exception

class ClickHandler(private val expressionField: TextView) {

    private fun lastNumberContainPoint(): Boolean {
        var index = expressionField.text.lastIndex

        while(index >= 0) {
            if (expressionField.text[index] !in '0'..'9') return expressionField.text[index] == '.'

            index--
        }

        return false
    }

    private fun canAddBracket(): Boolean {
        var openBrackets = 0
        var closeBrackets = 0

        expressionField.text.forEach {
            if (it == '(') openBrackets++
            if (it == ')') closeBrackets++
        }

        return openBrackets > closeBrackets
    }

    fun buttonDigitClick(digit: Char) {
        if (expressionField.text.isEmpty()
            || expressionField.text[expressionField.text.lastIndex] in '1'..'9'
            || expressionField.text[expressionField.text.lastIndex] == '+'
            || expressionField.text[expressionField.text.lastIndex] == '-'
            || expressionField.text[expressionField.text.lastIndex] == '*'
            || expressionField.text[expressionField.text.lastIndex] == '/'
            || expressionField.text[expressionField.text.lastIndex] == '.'
            || expressionField.text[expressionField.text.lastIndex] == '('
        ) {
            expressionField.append(digit.toString())

            return
        }

        if (expressionField.text[expressionField.text.lastIndex] == '0') {
            if (expressionField.text.lastIndex > 0
                && (expressionField.text[expressionField.text.lastIndex - 1].isDigit()
                        ||expressionField.text[expressionField.text.lastIndex - 1] == '.')) {
                expressionField.append(digit.toString())
            } else {
                expressionField.text = expressionField.text.subSequence(0, expressionField.text.lastIndex)
                expressionField.append(digit.toString())
            }
        }
    }

    fun buttonOperationClick(operation: Char) {
        if (expressionField.text.isEmpty()) {
            if (operation == '-') expressionField.append(operation.toString())
            return
        }

        if (expressionField.text[expressionField.text.lastIndex] in '0'..'9'
            || expressionField.text[expressionField.text.lastIndex] == '('
            || expressionField.text[expressionField.text.lastIndex] == ')') {
            expressionField.append(operation.toString())
        }
    }

    fun buttonPointClick() {
        if (expressionField.text.isEmpty()) return

        if (expressionField.text[expressionField.text.lastIndex] in '0'..'9') {
            if (!lastNumberContainPoint()) expressionField.append(".")
        }
    }

    fun buttonOpenBracketClick() {
        if (expressionField.text.isEmpty()
            || expressionField.text[expressionField.text.lastIndex] == '+'
            || expressionField.text[expressionField.text.lastIndex] == '-'
            || expressionField.text[expressionField.text.lastIndex] == '*'
            || expressionField.text[expressionField.text.lastIndex] == '/'
            ||expressionField.text[expressionField.text.lastIndex] == '(') expressionField.append("(")
    }

    fun buttonCloseBracketClick() {
        if (expressionField.text.isEmpty()) return

        if (expressionField.text[expressionField.text.lastIndex] in '0'..'9'
            ||expressionField.text[expressionField.text.lastIndex] == ')') {
            if (canAddBracket()) expressionField.append(")")
        }
    }

    fun buttonEqualClick() {
        if (expressionField.text.isEmpty()) return

        if (expressionField.text[expressionField.text.lastIndex] in '0'..'9'
            ||expressionField.text[expressionField.text.lastIndex] == ')') {
            try {
                expressionField.text = Parser().parse(expressionField.text.toString()).toString()
            } catch (e: Exception) {

            }
        }
    }

    fun buttonCClick() {
        if (expressionField.text.isEmpty()) return

        expressionField.text = expressionField.text.subSequence(0, expressionField.text.lastIndex)
    }

    fun buttonCEClick() {
        expressionField.text = ""
    }
}