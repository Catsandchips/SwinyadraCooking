package com.slouchingdog.android.swinyadracooking.domain.use_cases

import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository

class DeleteRecipeUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(id: String) {
        repository.deleteRecipe(id)
    }
}