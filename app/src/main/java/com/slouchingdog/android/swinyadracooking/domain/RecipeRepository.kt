package com.slouchingdog.android.swinyadracooking.domain

import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<List<RecipeDetailedEntity>>
    suspend fun addRecipe(recipeDetailedEntity: RecipeDetailedEntity)
    suspend fun updateRecipe(recipeDetailedEntity: RecipeDetailedEntity)
    suspend fun deleteRecipe(id: String)
    suspend fun getRecipeById(id: String): RecipeDetailedEntity
}