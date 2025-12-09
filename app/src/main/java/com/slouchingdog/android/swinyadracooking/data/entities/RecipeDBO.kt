package com.slouchingdog.android.swinyadracooking.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity

const val RECIPE_TABLE_NAME = "recipes"
typealias RecipeDBOList = List<RecipeDBO>

@Entity(tableName = RECIPE_TABLE_NAME)
data class RecipeDBO(
    @PrimaryKey
    val id: String,
    val name: String,
    val dishType: Int,
    val cookingTime: Int,
    val imageUri: String?,
    val calories: Double,
    val proteins: Double,
    val fats: Double,
    val carbons: Double
)

fun RecipeDBO.mapToEntity() = RecipeEntity(
    id = id,
    name = name,
    dishType = dishType,
    cookingTime = cookingTime,
    imageUri = imageUri,
    calories = calories,
    proteins = proteins,
    fats = fats,
    carbons = carbons
)

fun RecipeDBOList.mapToRecipeEntityList() = map { it.mapToEntity() }

