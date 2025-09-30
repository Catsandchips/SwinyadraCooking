package com.slouchingdog.android.swinyadracooking.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object RecipeListDestination

@Serializable
object ReadRecipeScreen

@Serializable
object UpdateRecipeScreen

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = RecipeListDestination){
        composable<RecipeListDestination> {
//            RecipeListScreen()
        }

        composable<ReadRecipeScreen> {
            Text("Read recipe")
        }

        composable<UpdateRecipeScreen> {
//            UpdateRecipeScreen()
        }

    }

}