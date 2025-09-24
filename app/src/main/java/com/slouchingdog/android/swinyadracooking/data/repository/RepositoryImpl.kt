package com.slouchingdog.android.swinyadracooking.data.repository

import android.content.Context
import androidx.room.Room
import com.slouchingdog.android.swinyadracooking.data.entities.mapToRecipeEntity
import com.slouchingdog.android.swinyadracooking.data.entities.mapToRecipeEntityList
import com.slouchingdog.android.swinyadracooking.data.local.RecipeDatabase
import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.mapToRecipeDBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DB_NAME = "SwinyadraCookingDB"

class RepositoryImpl private constructor(context: Context) : RecipeRepository {
    val db = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        DB_NAME
    ).build()

    val dao = db.recipeDao()

    override suspend fun getRecipes(): Flow<List<RecipeEntity>> =
        dao.getRecipeList().map { it.mapToRecipeEntityList() }

    override suspend fun addRecipe(recipeEntity: RecipeEntity) =
        dao.addRecipe(recipeEntity.mapToRecipeDBO())

    override suspend fun updateRecipe(recipeEntity: RecipeEntity) =
        dao.addRecipe(recipeEntity.mapToRecipeDBO())

    override suspend fun deleteRecipe(recipeEntity: RecipeEntity) =
        dao.deleteRecipeById(recipeEntity.id)

    override suspend fun getRecipeById(id: String): RecipeEntity =
        dao.getRecipeById(id).mapToRecipeEntity()

    companion object {
        private var INSTANCE: RepositoryImpl? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = RepositoryImpl(context)
            }
        }

        fun get(): RepositoryImpl {
            return INSTANCE ?: throw IllegalStateException("HabitsRepository must be initialized")
        }
    }
}