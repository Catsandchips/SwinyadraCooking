package com.slouchingdog.android.swinyadracooking.domain

import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<List<RecipeEntity>>
    suspend fun addRecipe(recipeEntity: RecipeEntity)
    suspend fun updateRecipe(recipeEntity: RecipeEntity)
    suspend fun deleteRecipe(recipeEntity: RecipeEntity)
    suspend fun getRecipeById(id: Int)
}