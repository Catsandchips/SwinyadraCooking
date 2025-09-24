package com.slouchingdog.android.swinyadracooking.domain.use_cases

import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity

class UpdateRecipeUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(recipeEntity: RecipeEntity) {
        repository.updateRecipe(recipeEntity)
    }
}