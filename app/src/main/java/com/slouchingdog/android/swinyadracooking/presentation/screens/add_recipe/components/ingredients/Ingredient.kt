package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients

import java.util.UUID

data class Ingredient(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val amount: Int = 0,
    val unitType: Int = 0
)