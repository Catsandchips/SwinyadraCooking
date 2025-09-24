package com.slouchingdog.android.swinyadracooking.domain.use_cases

import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

class GetRecipesUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(): Flow<List<RecipeEntity>> {
        return repository.getRecipes()
    }
}