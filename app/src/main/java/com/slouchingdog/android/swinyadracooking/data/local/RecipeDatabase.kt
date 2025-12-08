package com.slouchingdog.android.swinyadracooking.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.slouchingdog.android.swinyadracooking.data.entities.CookingStepDBO
import com.slouchingdog.android.swinyadracooking.data.entities.IngredientDBO
import com.slouchingdog.android.swinyadracooking.data.entities.RecipeDBO

@Database(entities = [RecipeDBO::class, IngredientDBO::class, CookingStepDBO::class], version = 4)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDAO
}