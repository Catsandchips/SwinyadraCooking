package com.slouchingdog.android.swinyadracooking.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity

typealias DetailedRecipeDboList = List<DetailedRecipeDBO>
data class DetailedRecipeDBO(
    @Embedded val recipeDBO: RecipeDBO,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredients: List<IngredientDBO>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val cookingSteps: List<CookingStepDBO>
)

fun DetailedRecipeDBO.mapToEntity() = RecipeDetailedEntity(
    recipeEntity = recipeDBO.mapToEntity(),
    ingredients = ingredients.mapToEntityList(),
    cookingSteps = cookingSteps.mapToEntityList()
)

fun DetailedRecipeDboList.mapToEntityList() = map { it.mapToEntity() }