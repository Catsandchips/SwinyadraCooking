package com.slouchingdog.android.swinyadracooking.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.slouchingdog.android.swinyadracooking.data.entities.RecipeDBO

@Database(entities = [RecipeDBO::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDAO
}