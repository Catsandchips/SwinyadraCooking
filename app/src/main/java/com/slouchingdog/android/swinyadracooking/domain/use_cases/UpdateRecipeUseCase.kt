package com.slouchingdog.android.swinyadracooking.domain.use_cases

import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity

class UpdateRecipeUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(recipeDetailedEntity: RecipeDetailedEntity) {
        repository.updateRecipe(recipeDetailedEntity)
    }
}