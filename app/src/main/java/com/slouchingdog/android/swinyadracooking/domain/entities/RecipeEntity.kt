package com.slouchingdog.android.swinyadracooking.domain.entities

import com.slouchingdog.android.swinyadracooking.data.entities.RecipeDBO
import java.util.UUID

data class RecipeEntity(
    val id: String?,
    val name: String,
    val dishType: Int,
    val cookingTime: Int,
    val imageUri: String?
)

fun RecipeEntity.mapToDBO() = RecipeDBO(
    id = id ?: UUID.randomUUID().toString(),
    name = name,
    dishType = dishType,
    cookingTime = cookingTime,
    imageUri = imageUri
)