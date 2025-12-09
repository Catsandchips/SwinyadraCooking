package com.slouchingdog.android.swinyadracooking.domain.entities

import com.slouchingdog.android.swinyadracooking.data.entities.IngredientDBO
import java.util.UUID

typealias IngredientEntityList = List<IngredientEntity>

data class IngredientEntity(
    val id: String?,
    val name: String = "",
    val recipeId: String,
    val amount: Double = 0.0,
    val unitType: Int = 0,
    val isUnitTypeExpanded: Boolean = false
)

fun IngredientEntity.mapToDBO() = IngredientDBO(
    id = id ?: UUID.randomUUID().toString(),
    name = name,
    recipeId = recipeId,
    amount = amount,
    unitType = unitType
)

fun IngredientEntityList.mapToDBOList() = map { it.mapToDBO() }