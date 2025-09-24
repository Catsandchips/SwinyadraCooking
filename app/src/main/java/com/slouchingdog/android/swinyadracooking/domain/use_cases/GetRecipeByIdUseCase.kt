package com.slouchingdog.android.swinyadracooking.domain.use_cases

import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity

class GetRecipeByIdUseCase(private val repository: RecipeRepository) {
    suspend operator fun invoke(id: String): RecipeEntity {
        return repository.getRecipeById(id)
    }
}