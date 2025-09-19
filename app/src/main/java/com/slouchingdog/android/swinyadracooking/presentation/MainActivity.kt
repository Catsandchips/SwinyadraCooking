package com.slouchingdog.android.swinyadracooking.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.AddRecipeScreen
import com.slouchingdog.android.swinyadracooking.presentation.theme.SwinyadraCookingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwinyadraCookingTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    AddRecipeScreen(innerPadding)
                }
            }
        }
    }
}