package com.slouchingdog.android.swinyadracooking.data.repository

import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

class RepositoryImpl: RecipeRepository {
    override suspend fun getRecipes(): Flow<List<RecipeEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun addRecipe(recipeEntity: RecipeEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipe(recipeEntity: RecipeEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(recipeEntity: RecipeEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeById(id: Int) {
        TODO("Not yet implemented")
    }
}