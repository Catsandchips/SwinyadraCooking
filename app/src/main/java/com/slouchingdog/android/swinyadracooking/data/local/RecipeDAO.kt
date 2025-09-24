package com.slouchingdog.android.swinyadracooking.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.slouchingdog.android.swinyadracooking.data.entities.RECIPE_TABLE_NAME
import com.slouchingdog.android.swinyadracooking.data.entities.RecipeDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM $RECIPE_TABLE_NAME")
    fun getRecipeList(): Flow<List<RecipeDBO>>

    @Query("SELECT * FROM $RECIPE_TABLE_NAME WHERE id=:id")
    suspend fun getRecipeById(id: String): RecipeDBO

    @Insert(onConflict = REPLACE)
    suspend fun addRecipe(recipeDBO: RecipeDBO)

    @Query("DELETE FROM $RECIPE_TABLE_NAME WHERE id = :id")
    suspend fun deleteRecipeById(id: String)
}