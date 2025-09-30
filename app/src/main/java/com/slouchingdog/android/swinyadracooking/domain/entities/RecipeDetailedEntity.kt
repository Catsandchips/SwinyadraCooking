package com.slouchingdog.android.swinyadracooking.domain.entities

import com.slouchingdog.android.swinyadracooking.data.entities.DetailedRecipeDBO

typealias RecipeDetailedEntityList = List<RecipeDetailedEntity>

data class RecipeDetailedEntity(
    val recipeEntity: RecipeEntity,
    val ingredients: List<IngredientEntity>,
    val cookingSteps: List<CookingStepEntity>
)

fun RecipeDetailedEntity.mapToDetailedRecipeDBO() = DetailedRecipeDBO(
    recipeDBO = recipeEntity.mapToDBO(),
    ingredients = ingredients.mapToDBOList(),
    cookingSteps = cookingSteps.mapToDBOList()
)

fun RecipeDetailedEntityList.mapToRecipeDBOList() = map { it.mapToDetailedRecipeDBO() }