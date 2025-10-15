package com.example.perceptronlearning

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    var rows by remember { mutableStateOf("") }
    var cols by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Row Input
        OutlinedTextField(
            value = rows,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    rows = input
                }
            },
            label = { Text("Enter number of rows (0–10)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Column Input
        OutlinedTextField(
            value = cols,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    cols = input
                }
            },
            label = { Text("Enter number of columns (0–10)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Continue Button
        Button(
            onClick = {
                val r = (rows.toIntOrNull() ?: 1).coerceIn(1, 10)
                val c = (cols.toIntOrNull() ?: 1).coerceIn(1, 10)
                navController.navigate("PerceptronUI/$r/$c")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}
