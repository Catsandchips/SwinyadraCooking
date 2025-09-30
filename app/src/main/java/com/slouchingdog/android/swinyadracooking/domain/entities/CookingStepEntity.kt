package com.slouchingdog.android.swinyadracooking.domain.entities

import com.slouchingdog.android.swinyadracooking.data.entities.CookingStepDBO
import java.util.UUID

typealias CookingStepEntityList = List<CookingStepEntity>
data class CookingStepEntity(
    val id: String?,
    val recipeId: String,
    val description: String = ""
)

fun CookingStepEntity.mapToDBO() = CookingStepDBO(
    id = id ?: UUID.randomUUID().toString(),
    recipeId = recipeId,
    description = description
)

fun CookingStepEntityList.mapToDBOList() = map { it.mapToDBO() }