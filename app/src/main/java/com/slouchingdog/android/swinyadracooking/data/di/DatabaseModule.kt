package com.slouchingdog.android.swinyadracooking.data.di

import android.content.Context
import androidx.room.Room
import com.slouchingdog.android.swinyadracooking.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DB_NAME = "SwinyadraCookingDB"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRecipeDataBase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(context, RecipeDatabase::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideRecipeDAO(recipeDatabase: RecipeDatabase) = recipeDatabase.recipeDao()
}