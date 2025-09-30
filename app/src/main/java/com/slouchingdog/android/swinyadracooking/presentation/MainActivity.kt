package com.slouchingdog.android.swinyadracooking.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.UpdateRecipeScreen
import com.slouchingdog.android.swinyadracooking.presentation.theme.SwinyadraCookingTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwinyadraCookingTheme {
                //AddRecipeScreen()
                UpdateRecipeScreen()
            }
        }
    }
}