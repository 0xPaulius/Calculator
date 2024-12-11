package com.example.androidcalculator

import org.junit.Test
import org.junit.Assert.*

class CalculatorTest {
    private val calculator = Calculator()

    @Test
    fun addition_isCorrect() {
        assertEquals("4.0", calculator.calculate(2.0, 2.0, "+"))
    }

    @Test
    fun subtraction_isCorrect() {
        assertEquals("3.0", calculator.calculate(5.0, 2.0, "-"))
    }

    @Test
    fun multiplication_isCorrect() {
        assertEquals("10.0", calculator.calculate(2.0, 5.0, "×"))
    }

    @Test
    fun division_isCorrect() {
        assertEquals("2.0", calculator.calculate(10.0, 5.0, "÷"))
    }

    @Test
    fun divisionByZero_returnsError() {
        assertEquals("Error", calculator.calculate(5.0, 0.0, "÷"))
    }

    @Test
    fun squareRoot_isCorrect() {
        assertEquals("2.0", calculator.sqrt(4.0))
    }

    @Test
    fun negativeSquareRoot_returnsError() {
        assertEquals("Error", calculator.sqrt(-4.0))
    }
    @Test
    fun negativeNumbers_workCorrectly() {
        assertEquals("-7.0", calculator.calculate(-5.0, -2.0, "+"))
    }

    @Test
    fun invalidOperation_returnsError() {
        assertEquals("Error", calculator.calculate(5.0, 2.0, "*"))
    }
}