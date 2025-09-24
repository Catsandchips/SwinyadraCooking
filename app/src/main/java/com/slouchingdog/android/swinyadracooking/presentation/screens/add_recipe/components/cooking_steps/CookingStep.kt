package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.cooking_steps

import java.util.UUID

data class CookingStep(
    val id: String = UUID.randomUUID().toString(),
    val stepDescription: String = ""
)