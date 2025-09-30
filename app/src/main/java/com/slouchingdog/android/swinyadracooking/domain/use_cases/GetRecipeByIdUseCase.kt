package com.slouchingdog.android.swinyadracooking.domain.use_cases

import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity

class GetRecipeByIdUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(id: String): RecipeDetailedEntity {
        return repository.getRecipeById(id)
    }
}