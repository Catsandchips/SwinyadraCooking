package com.slouchingdog.android.swinyadracooking.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.slouchingdog.android.swinyadracooking.domain.entities.CookingStepEntity

const val COOKING_STEP_TABLE_NAME = "cooking_steps"
typealias CookingStepDBOList = List<CookingStepDBO>
@Entity(
    tableName = COOKING_STEP_TABLE_NAME, foreignKeys = [ForeignKey(
        entity = RecipeDBO::class,
        parentColumns = ["id"],
        childColumns = ["recipeId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CookingStepDBO(
    @PrimaryKey
    val id: String,
    val recipeId: String,
    val description: String
)

fun CookingStepDBO.mapToEntity() = CookingStepEntity(
    id = id,
    recipeId = recipeId,
    description = description
)

fun CookingStepDBOList.mapToEntityList() = map { it.mapToEntity() }