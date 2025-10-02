package com.slouchingdog.android.swinyadracooking.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.slouchingdog.android.swinyadracooking.presentation.screens.ReadRecipeScreen
import com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list.RecipeListScreen
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.UpdateRecipeScreen
import kotlinx.serialization.Serializable

@Serializable
object RecipeListDestination

@Serializable
data class ReadRecipeDestination(val recipeId: String)

@Serializable
data class UpdateRecipeDestination(val recipeId: String? = null)

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RecipeListDestination) {
        composable<RecipeListDestination> {
            RecipeListScreen(onRecipeClick = {
                navController.navigate(ReadRecipeDestination(it))
            }, onAddRecipeClick = {
                navController.navigate(UpdateRecipeDestination())
            })
        }

        composable<ReadRecipeDestination> { backStackEntry ->
            val readRecipeDestination: ReadRecipeDestination = backStackEntry.toRoute()
            ReadRecipeScreen(readRecipeDestination.recipeId, onEditButtonClick = {
                navController.navigate(UpdateRecipeDestination(it))
            })
        }

        composable<UpdateRecipeDestination> { backStackEntry ->
            val updateRecipeDestination: UpdateRecipeDestination = backStackEntry.toRoute()
            UpdateRecipeScreen(updateRecipeDestination.recipeId)
        }

    }

}