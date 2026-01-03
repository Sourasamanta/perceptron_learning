package com.example.perceptronlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.perceptronlearning.ui.theme.PerceptronLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PerceptronLearningTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "MainScreen"
                ) {
                    composable("MainScreen") {
                        MainScreen(navController)
                    }

                    composable(
                        route = "PerceptronUI/{rows}/{columns}",
                        arguments = listOf(
                            navArgument("rows") { type = NavType.IntType },
                            navArgument("columns") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val r = backStackEntry.arguments?.getInt("rows") ?: 1
                        val c = backStackEntry.arguments?.getInt("columns") ?: 1
                        LEDScreen(navController, r, c)
                    }
                }
            }
        }
    }
}
