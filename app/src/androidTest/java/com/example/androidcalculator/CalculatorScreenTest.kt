package com.example.androidcalculator

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidcalculator.ui.theme.AndroidCalculatorTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorScreenTest {
    @get:Rule
    val composeRule = createComposeRule()


    @Test
    fun multiplication_worksCorrectly() {
    composeRule.setContent {
        AndroidCalculatorTheme {
            calculatorScreen()
        }
    }

    composeRule.onNodeWithText("3").performClick()
    composeRule.onNodeWithText("×").performClick()
    composeRule.onNodeWithText("4").performClick()
    composeRule.onNodeWithText("=").performClick()

    composeRule.onNodeWithText("12.0").assertExists()
    }

@Test
fun squareRoot_worksCorrectly() {
    composeRule.setContent {
        AndroidCalculatorTheme {
            calculatorScreen()
        }
    }

    composeRule.onNodeWithText("9").performClick()
    composeRule.onNodeWithText("√").performClick()

    composeRule.onNodeWithText("3.0").assertExists()
}

@Test
fun deleteButton_worksCorrectly() {
    composeRule.setContent {
        AndroidCalculatorTheme {
            calculatorScreen()
        }
    }

    composeRule.onNodeWithText("1").performClick()
    composeRule.onNodeWithText("2").performClick()
    composeRule.onNodeWithText("3").performClick()
    composeRule.onNodeWithText("Del").performClick()

    composeRule.onNodeWithText("12").assertExists()
    }
}