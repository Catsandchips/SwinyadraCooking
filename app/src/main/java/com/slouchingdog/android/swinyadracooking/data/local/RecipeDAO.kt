package com.slouchingdog.android.swinyadracooking.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.slouchingdog.android.swinyadracooking.data.entities.CookingStepDBO
import com.slouchingdog.android.swinyadracooking.data.entities.DetailedRecipeDBO
import com.slouchingdog.android.swinyadracooking.data.entities.IngredientDBO
import com.slouchingdog.android.swinyadracooking.data.entities.RECIPE_TABLE_NAME
import com.slouchingdog.android.swinyadracooking.data.entities.RecipeDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDAO {
    @Insert(onConflict = REPLACE)
    suspend fun insertRecipe(recipe: RecipeDBO)

    @Insert
    suspend fun insertIngredients(ingredients: List<IngredientDBO>)

    @Insert
    suspend fun insertCookingSteps(steps: List<CookingStepDBO>)

    @Transaction
    @Query("SELECT * FROM $RECIPE_TABLE_NAME WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: String): DetailedRecipeDBO

    @Transaction
    @Query("SELECT * FROM $RECIPE_TABLE_NAME")
    fun getRecipeList(): Flow<List<DetailedRecipeDBO>>

    @Query("DELETE FROM $RECIPE_TABLE_NAME WHERE id = :recipeId")
    suspend fun deleteRecipeById(recipeId: String)
}