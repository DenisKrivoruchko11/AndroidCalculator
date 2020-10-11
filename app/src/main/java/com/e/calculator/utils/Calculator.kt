package com.e.calculator.utils

import java.lang.ArithmeticException
import java.util.*

private fun getItems(expression: String): List<String> {
    var current = ""
    val result = mutableListOf<String>()

    expression.forEachIndexed { index, it ->
        when (it) {
            in '0'..'9' -> current += it
            '.' -> current += it
            else -> {
                if (it == '-' && (index == 0 || expression[index - 1] == '(')) {
                    current += it
                } else {
                    if (current.isNotEmpty()) result.add(current)

                    result.add(it.toString())

                    current = ""
                }
            }
        }
    }

    if (current.isNotEmpty()) result.add(current)

    return result
}

private fun removeUseLessBrackets(items: List<String>): List<String> {
    val result = mutableListOf<String>()

    var shouldRemove = false

    items.forEachIndexed { index, it ->
        if (it == "(" && items[index + 2] == ")") shouldRemove = true
        else if (it == ")" && shouldRemove) shouldRemove = false
        else result.add(it)
    }

    return result
}

private fun handleOperation(
    numberStack: Stack<Double>,
    operationStack: Stack<Char>,
    operation: Char
) {
    if (operation == '+' || operation == '-') {
        while (!operationStack.empty()
            && (operationStack.peek() == '+'
                    || operationStack.peek() == '-'
                    || operationStack.peek() == '*'
                    || operationStack.peek() == '/')
        ) {
            val second = numberStack.pop()
            val first = numberStack.pop()

            when (operationStack.pop()) {
                '+' -> numberStack.push(first + second)
                '-' -> numberStack.push(first - second)
                '*' -> numberStack.push(first * second)
                '/' -> if (second != 0.0) numberStack.push(first / second) else
                    throw ArithmeticException("Division by zero attempt.")
            }
        }
    } else {
        while (!operationStack.empty() &&
            (operationStack.peek() == '*' || operationStack.peek() == '/')
        ) {
            val second = numberStack.pop()
            val first = numberStack.pop()

            when (operationStack.pop()) {
                '*' -> numberStack.push(first * second)
                '/' -> if (second != 0.0) numberStack.push(first / second) else
                    throw ArithmeticException("Division by zero attempt.")
            }
        }
    }

    operationStack.push(operation)
}

private fun handleCloseBracket(numberStack: Stack<Double>, operationStack: Stack<Char>) {
    while (!operationStack.empty() && operationStack.peek() != '(') {
        val second = numberStack.pop()
        val first = numberStack.pop()

        when (operationStack.pop()) {
            '+' -> numberStack.push(first + second)
            '-' -> numberStack.push(first - second)
            '*' -> numberStack.push(first * second)
            '/' -> if (second != 0.0) numberStack.push(first / second) else
                throw ArithmeticException("Division by zero attempt.")
        }
    }

    operationStack.pop()
}

private fun calculate(items: List<String>): Double {
    val numberStack = Stack<Double>()
    val operationStack = Stack<Char>()

    items.forEach {
        when {
            it.toDoubleOrNull() != null -> numberStack.push(it.toDouble())
            it == "(" -> operationStack.push(it[0])
            it == ")" -> handleCloseBracket(numberStack, operationStack)
            it == "+" || it == "-" || it == "*" || it == "/" ->
                handleOperation(numberStack, operationStack, it[0])
        }
    }

    while (!operationStack.empty()) {
        val second = numberStack.pop()
        val first = numberStack.pop()

        when (operationStack.pop()) {
            '+' -> numberStack.push(first + second)
            '-' -> numberStack.push(first - second)
            '*' -> numberStack.push(first * second)
            '/' -> if (second != 0.0) numberStack.push(first / second) else
                throw ArithmeticException("Division by zero attempt.")
        }
    }

    return numberStack.pop()
}

class Calculator {

    fun calculate(expression: String): Double {
        val items = removeUseLessBrackets(getItems(expression))

        return calculate(items)
    }
}