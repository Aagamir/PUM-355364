package com.example.zadanie2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calc()
        }
    }
}

@Composable
fun Calc() {
    var firstInput by rememberSaveable { mutableStateOf("") }
    var secondInput by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("N/A") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = firstInput,
            onValueChange = { firstInput = it },
            label = { Text("Enter first number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = secondInput,
            onValueChange = { secondInput = it },
            label = { Text("Enter second number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            val buttonModifier = Modifier.weight(1f).width(20.dp)

            Button(
                modifier = buttonModifier,
                shape = RectangleShape,
                onClick = {
                    val n1 = firstInput.toIntOrNull()
                    val n2 = secondInput.toIntOrNull()
                    result = if (n1 != null && n2 != null) (n1 + n2).toString() else "Error"
                }
            ) {
                Text("+", fontSize = 20.sp)
            }

            Button(
                modifier = buttonModifier,
                shape = RectangleShape,
                onClick = {
                    val n1 = firstInput.toIntOrNull()
                    val n2 = secondInput.toIntOrNull()
                    result = if (n1 != null && n2 != null) (n1 - n2).toString() else "Error"
                }
            ) {
                Text("-", fontSize = 20.sp)
            }

            Button(
                modifier = buttonModifier,
                shape = RectangleShape,

                onClick = {
                    val n1 = firstInput.toIntOrNull()
                    val n2 = secondInput.toIntOrNull()
                    result = if (n1 != null && n2 != null) (n1 * n2).toString() else "Error"
                }
            ) {
                Text("×", fontSize = 20.sp)
            }

            Button(
                modifier = buttonModifier,
                shape = RectangleShape,
                onClick = {
                    val n1 = firstInput.toIntOrNull()
                    val n2 = secondInput.toIntOrNull()
                    result = when {
                        n1 == null || n2 == null -> "Error"
                        n2 == 0 -> "Error" // Obsługa wyjątku dzielenia przez 0 bez crashowania aplikacji
                        else -> (n1.toFloat() / n2).toString()
                    }
                }
            ) {
                Text("÷", fontSize = 20.sp)
            }
        }

        Text(
            text = "Result: $result",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}