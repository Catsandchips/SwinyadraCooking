package com.slouchingdog.android.swinyadracooking.domain.entities

data class RecipeEntity(
    val id: Int,
    val name: String,
    val cookingTime: Int,
    val portionsCount: Int
)