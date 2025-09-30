//package com.slouchingdog.android.swinyadracooking.data.repository
//
//import android.content.Context
//import androidx.room.Room
//import com.slouchingdog.android.swinyadracooking.data.entities.mapToEntity
//import com.slouchingdog.android.swinyadracooking.data.entities.mapToEntityList
//import com.slouchingdog.android.swinyadracooking.data.local.RecipeDatabase
//import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
//import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity
//import com.slouchingdog.android.swinyadracooking.domain.entities.mapToDBO
//import com.slouchingdog.android.swinyadracooking.domain.entities.mapToDBOList
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//const val DB_NAME = "SwinyadraCookingDB"
//
//class RepositoryImpl private constructor(context: Context) : RecipeRepository {
//    val db = Room.databaseBuilder(
//        context,
//        RecipeDatabase::class.java,
//        DB_NAME
//    ).build()
//
//    val dao = db.recipeDao()
//
//    override suspend fun getRecipes(): Flow<List<RecipeDetailedEntity>> =
//        dao.getRecipeList().map { it.mapToEntityList() }
//
//    override suspend fun addRecipe(recipeDetailedEntity: RecipeDetailedEntity) {
//        dao.insertRecipe(recipeDetailedEntity.recipeEntity.mapToDBO())
//        dao.insertIngredients(recipeDetailedEntity.ingredients.mapToDBOList())
//        dao.insertCookingSteps(recipeDetailedEntity.cookingSteps.mapToDBOList())
//    }
//
//    override suspend fun updateRecipe(recipeDetailedEntity: RecipeDetailedEntity) {}
//
//    override suspend fun deleteRecipe(id: String) = dao.deleteRecipeById(id)
//
//    override suspend fun getRecipeById(id: String): RecipeDetailedEntity = dao.getRecipeById(id).mapToEntity()
//
//    companion object {
//        private var INSTANCE: RepositoryImpl? = null
//
//        fun initialize(context: Context) {
//            if (INSTANCE == null) {
//                INSTANCE = RepositoryImpl(context)
//            }
//        }
//
//        fun get(): RepositoryImpl {
//            return INSTANCE ?: throw IllegalStateException("HabitsRepository must be initialized")
//        }
//    }
//}