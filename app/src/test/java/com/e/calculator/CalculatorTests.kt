package com.e.calculator

import com.e.calculator.utils.Calculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import java.lang.ArithmeticException

class CalculatorTests {

    private val calculator = Calculator()

    @Test
    fun `calculate should throw ArithmeticException when trying to divide by zero`() {
        assertThrows(ArithmeticException::class.java) { calculator.calculate("11/(10-5*2)") }
    }

    @Test
    fun `calculate should return right value when expression does not contain operators`() {
        assertEquals(calculator.calculate("-0.11"), -0.11)
    }

    @Test
    fun `calculate should return right value when expression contain one operator`() {
        assertEquals(calculator.calculate("-0.001+1"), 0.999)
    }

    @Test
    fun `calculate should return right value when expression contain some brackets`() {
        assertEquals(calculator.calculate("(10+8)/3+(19-7)*2"), 30.0)
    }

    @Test
    fun `calculate should return right value when expression contain all types of operators`() {
        assertEquals(calculator.calculate("10+(3*15)/5-((4+99)-(-0.03+(-0.0)*18-1.1))"), -85.13)
    }
}
