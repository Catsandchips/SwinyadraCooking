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
    val portionsCount: Int
)

fun RecipeDBO.mapToRecipeEntity() = RecipeEntity(
    id = id,
    name = name,
    dishType = dishType,
    cookingTime = cookingTime,
    portionsCount = portionsCount
)

fun RecipeDBOList.mapToRecipeEntityList() = map { it.mapToRecipeEntity() }

