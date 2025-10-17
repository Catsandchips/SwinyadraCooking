package com.slouchingdog.android.swinyadracooking.data.repository

import com.slouchingdog.android.swinyadracooking.data.entities.mapToEntity
import com.slouchingdog.android.swinyadracooking.data.entities.mapToEntityList
import com.slouchingdog.android.swinyadracooking.data.local.RecipeDAO
import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.mapToDBO
import com.slouchingdog.android.swinyadracooking.domain.entities.mapToDBOList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class RecipeRepositoryImpl(val dao: RecipeDAO) : RecipeRepository {

    override suspend fun getRecipes(): Flow<List<RecipeDetailedEntity>> =
        dao.getRecipeList().map { it.mapToEntityList() }

    override suspend fun addRecipe(recipeDetailedEntity: RecipeDetailedEntity) {
        dao.insertRecipe(recipeDetailedEntity.recipeEntity.mapToDBO())
        dao.insertIngredients(recipeDetailedEntity.ingredients.mapToDBOList())
        dao.insertCookingSteps(recipeDetailedEntity.cookingSteps.mapToDBOList())
    }

    override suspend fun deleteRecipe(id: String) = dao.deleteRecipeById(id)

    override suspend fun getRecipeById(id: String): Flow<RecipeDetailedEntity> =
        dao.getRecipeById(id).map { it.mapToEntity() }
}