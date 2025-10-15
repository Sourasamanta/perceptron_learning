package com.example.perceptronlearning

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LEDScreen(navController: NavController, row: Int, column: Int) {
    // Create a separate Perceptron instance for this grid
    val perceptron = remember { Perceptron(row * column, 0.1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LED Grid") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LED(row, column, perceptron)
        }
    }
}

@Composable
fun LED(row: Int, column: Int, perceptron: Perceptron) {
    val inputPattern = remember { mutableStateListOf(*IntArray(row * column) { 0 }.toTypedArray()) }
    var output by remember { mutableStateOf(-1) }
    var target by remember { mutableStateOf<Int?>(null) } // null means nothing selected
    var flag by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // LED grid
        for (i in 0 until row) {
            Row {
                for (j in 0 until column) {
                    val index = i * column + j
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(if (inputPattern[index] == 1) Color.Red else Color.LightGray)
                            .border(1.5.dp, Color.White)
                            .clickable {
                                inputPattern[index] = 1 - inputPattern[index]
                            }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // RadioButtons for -1 and +1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = target == -1,
                    onClick = { target = -1; flag = 1 },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("-1")
            }

            Spacer(modifier = Modifier.width(32.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = target == 1,
                    onClick = { target = 1; flag = 1 },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Green)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("+1")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Train & Predict buttons
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                if (flag == 1 && target != null) {
                    // Map 0->-1, 1->+1 for perceptron input
                    val inputs = inputPattern.map { if (it == 0) -1 else 1 }.toIntArray()
                    output = perceptron.predict(inputs)
                    perceptron.train(inputs, target!!)
                    flag = 0
                    inputPattern.replaceAll { 0 }
                    target = null
                }
            }) {
                Text("Train")
            }

            Button(onClick = {
                val inputs = inputPattern.map { if (it == 0) -1 else 1 }.toIntArray()
                output = perceptron.predict(inputs)
                inputPattern.replaceAll { 0 }
                target = null
            }) {
                Text("Predict")
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(text = "output = $output")
        Text(text = "y = ${perceptron.y}")
    }
}
