package com.slouchingdog.android.swinyadracooking.domain.entities

import com.slouchingdog.android.swinyadracooking.data.entities.RecipeDBO

typealias RecipeEntityList = List<RecipeEntity>

data class RecipeEntity(
    val id: String,
    val name: String,
    val dishType: Int,
    val cookingTime: Int,
    val portionsCount: Int
)

fun RecipeEntity.mapToRecipeDBO() = RecipeDBO(
    id = id,
    name = name,
    dishType = dishType,
    cookingTime = cookingTime,
    portionsCount = portionsCount
)

fun RecipeEntityList.mapToRecipeDBOList() = map { it.mapToRecipeDBO() }