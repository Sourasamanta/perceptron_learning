package com.example.perceptronlearning

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LEDScreen(
    navController: NavController,
    row: Int,
    column: Int
) {
    val perceptron = remember { Perceptron(row * column, 0.1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LED Grid • ${row}×$column") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        LED(
            row = row,
            column = column,
            perceptron = perceptron,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun LED(
    row: Int,
    column: Int,
    perceptron: Perceptron,
    modifier: Modifier = Modifier
) {
    val total = row * column

    val inputPattern = remember {
        mutableStateListOf(*IntArray(total) { 0 }.toTypedArray())
    }

    var output by remember { mutableStateOf(-1) }
    var target by remember { mutableStateOf<Int?>(null) }
    var lastScore by remember { mutableStateOf(0.0) }

    fun clearGrid() {
        inputPattern.indices.forEach { inputPattern[it] = 0 }
    }

    fun getInputs(): IntArray =
        inputPattern.map { if (it == 0) -1 else 1 }.toIntArray()

    val spacing = 8.dp
    val cellSize = (320 / max(1, column)).coerceIn(26, 50).dp
    val gridHeight = calculateGridHeight(row, cellSize, spacing)

    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        item {
            ElevatedCard(shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Draw a Pattern", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Tap cells to toggle LEDs. Select a target label to train, or directly predict.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            ElevatedCard(shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("LED Grid", style = MaterialTheme.typography.titleMedium)
                        TextButton(onClick = { clearGrid(); target = null }) {
                            Icon(Icons.Default.Delete, null)
                            Spacer(Modifier.width(6.dp))
                            Text("Clear")
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(column),
                        userScrollEnabled = false,
                        verticalArrangement = Arrangement.spacedBy(spacing),
                        horizontalArrangement = Arrangement.spacedBy(spacing),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(gridHeight)
                    ) {
                        items((0 until total).toList()) { index ->
                            val isOn = inputPattern[index] == 1

                            val bg by animateColorAsState(
                                if (isOn)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.surfaceVariant,
                                label = ""
                            )

                            Box(
                                modifier = Modifier
                                    .size(cellSize)
                                    .border(
                                        1.dp,
                                        MaterialTheme.colorScheme.outlineVariant,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .background(bg, RoundedCornerShape(10.dp))
                                    .clickable {
                                        inputPattern[index] = 1 - inputPattern[index]
                                    }
                            )
                        }
                    }
                }
            }
        }

        item {
            ElevatedCard(shape = RoundedCornerShape(16.dp)) {
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Training Controls", style = MaterialTheme.typography.titleMedium)

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        FilterChip(
                            selected = target == -1,
                            onClick = { target = -1 },
                            label = { Text("-1") }
                        )
                        FilterChip(
                            selected = target == 1,
                            onClick = { target = 1 },
                            label = { Text("+1") }
                        )
                    }

                    Divider()

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(
                            enabled = target != null,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                val inputs = getInputs()
                                output = perceptron.predict(inputs)
                                perceptron.train(inputs, target!!)
                                lastScore = perceptron.y
                                clearGrid()
                                target = null
                            }
                        ) {
                            Icon(Icons.Default.Build, null)
                            Spacer(Modifier.width(8.dp))
                            Text("Train")
                        }

                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                val inputs = getInputs()
                                output = perceptron.predict(inputs)
                                lastScore = perceptron.y
                                clearGrid()
                                target = null
                            }
                        ) {
                            Icon(Icons.Default.Send, null)
                            Spacer(Modifier.width(8.dp))
                            Text("Predict")
                        }
                    }
                }
            }
        }

        item {
            ElevatedCard(shape = RoundedCornerShape(16.dp)) {
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Prediction Result", style = MaterialTheme.typography.titleMedium)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Output")
                        Text(output.toString(), style = MaterialTheme.typography.titleMedium)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Raw score (y)")
                        Text(String.format("%.4f", lastScore), style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

private fun calculateGridHeight(
    rows: Int,
    cellSize: Dp,
    spacing: Dp
): Dp {
    return if (rows <= 0) 0.dp else (cellSize * rows) + (spacing * (rows - 1))
}
