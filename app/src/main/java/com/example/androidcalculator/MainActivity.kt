package com.example.androidcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcalculator.ui.theme.AndroidCalculatorTheme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    calculatorScreen()
                }
            }
        }
    }
}

@Composable
fun calculatorScreen() {

    var display by remember { mutableStateOf("0") }
    var firstNum by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var startNewNumber by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = display,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 32.sp,
                textAlign = TextAlign.End
            ),
            readOnly = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    display = "0"
                    firstNum = ""
                    operation = ""
                    startNewNumber = true
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("C", fontSize = 24.sp)
            }


            Button(
                onClick = {
                    display = if (display.length > 1) display.dropLast(1) else "0"
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Del", fontSize = 24.sp)
            }


            Button(
                onClick = {
                    val number = display.toDoubleOrNull()
                    if (number != null && number >= 0) {
                        display = sqrt(number).toString()
                    }
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("√", fontSize = 24.sp)
            }


            Button(
                onClick = {
                    operation = "÷"
                    firstNum = display
                    startNewNumber = true
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("÷", fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        for (row in listOf(
            listOf("7", "8", "9", "×"),
            listOf("4", "5", "6", "-"),
            listOf("1", "2", "3", "+"),
            listOf("±", "0", ".", "=")
        )) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (button in row) {
                    Button(
                        onClick = {
                            when (button) {
                                in "0123456789" -> {
                                    if (startNewNumber) {
                                        display = button
                                        startNewNumber = false
                                    } else {
                                        display += button
                                    }
                                }
                                "." -> {
                                    if (!display.contains(".")) {
                                        if (startNewNumber) {
                                            display = "0."
                                            startNewNumber = false
                                        } else {
                                            display += "."
                                        }
                                    }
                                }
                                "±" -> {
                                    display = if (display.startsWith("-")) {
                                        display.substring(1)
                                    } else {
                                        "-$display"
                                    }
                                }
                                in listOf("+", "-", "×", "÷") -> {
                                    operation = button
                                    firstNum = display
                                    startNewNumber = true
                                }
                                "=" -> {
                                    if (firstNum.isNotEmpty() && operation.isNotEmpty()) {
                                        val num1 = firstNum.toDouble()
                                        val num2 = display.toDouble()
                                        display = when (operation) {
                                            "+" -> (num1 + num2).toString()
                                            "-" -> (num1 - num2).toString()
                                            "×" -> (num1 * num2).toString()
                                            "÷" -> if (num2 != 0.0) (num1 / num2).toString() else "Error"
                                            else -> display
                                        }
                                        firstNum = ""
                                        operation = ""
                                        startNewNumber = true
                                    }
                                }
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = when (button) {
                                in "0123456789." -> MaterialTheme.colorScheme.tertiary
                                in listOf("+", "-", "×", "÷", "=") -> MaterialTheme.colorScheme.primary
                                else -> MaterialTheme.colorScheme.secondary
                            }
                        )
                    ) {
                        Text(button, fontSize = 24.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
class Calculator {
    fun calculate(num1: Double, num2: Double, operation: String): String {
        return when (operation) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "×" -> (num1 * num2).toString()
            "÷" -> if (num2 != 0.0) (num1 / num2).toString() else "Error"
            else -> "Error"
        }
    }

    fun sqrt(number: Double): String {
        return if (number >= 0) kotlin.math.sqrt(number).toString() else "Error"
    }
}