package com.example.perceptronlearning

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var rows by remember { mutableStateOf("") }
    var cols by remember { mutableStateOf("") }

    val rVal = rows.toIntOrNull()
    val cVal = cols.toIntOrNull()

    val isValid = (rVal != null && cVal != null && rVal in 1..10 && cVal in 1..10)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perceptron LED Trainer") },
                actions = {}
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.deep_learning),
                contentDescription = "LED Grid Illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(bottom = 16.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Configure your LED grid",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Choose rows and columns (1–10). You can then draw a pattern and train the perceptron.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = rows,
                onValueChange = { input -> if (input.all { it.isDigit() } && input.length <= 2) rows = input },
                label = { Text("Rows (1–10)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (rows.isNotBlank() && (rVal == null || rVal !in 1..10)) {
                        Text("Rows must be between 1 and 10")
                    }
                },
                isError = rows.isNotBlank() && (rVal == null || rVal !in 1..10)
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = cols,
                onValueChange = { input -> if (input.all { it.isDigit() } && input.length <= 2) cols = input },
                label = { Text("Columns (1–10)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (cols.isNotBlank() && (cVal == null || cVal !in 1..10)) {
                        Text("Columns must be between 1 and 10")
                    }
                },
                isError = cols.isNotBlank() && (cVal == null || cVal !in 1..10)
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    val r = (rVal ?: 1).coerceIn(1, 10)
                    val c = (cVal ?: 1).coerceIn(1, 10)
                    navController.navigate("PerceptronUI/$r/$c")
                },
                enabled = isValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Tip: Start with small grids (e.g., 3×3) for faster training.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
