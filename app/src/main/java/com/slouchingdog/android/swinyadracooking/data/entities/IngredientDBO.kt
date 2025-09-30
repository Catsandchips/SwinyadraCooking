package com.slouchingdog.android.swinyadracooking.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.slouchingdog.android.swinyadracooking.domain.entities.IngredientEntity

const val INGREDIENT_TABLE_NAME = "ingredients"
typealias IngredientDBOList = List<IngredientDBO>
@Entity(
    tableName = INGREDIENT_TABLE_NAME, foreignKeys = [ForeignKey(
        entity = RecipeDBO::class,
        parentColumns = ["id"],
        childColumns = ["recipeId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class IngredientDBO(
    @PrimaryKey
    val id: String,
    val recipeId: String,
    val name: String,
    val amount: Int,
    val unitType: Int
)

fun IngredientDBO.mapToIngredientEntity() = IngredientEntity(
    id = id,
    recipeId = recipeId,
    name = name,
    amount = amount,
    unitType = unitType,
    isUnitTypeExpanded = false
)

fun IngredientDBOList.mapToEntityList() = map { it.mapToIngredientEntity() }