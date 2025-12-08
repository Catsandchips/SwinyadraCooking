package com.slouchingdog.android.swinyadracooking.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.slouchingdog.android.swinyadracooking.data.entities.RECIPE_TABLE_NAME
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
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE ${RECIPE_TABLE_NAME} ADD COLUMN imageUri TEXT")
                db.execSQL("ALTER TABLE ${RECIPE_TABLE_NAME} DROP COLUMN portionsCount")
            }
        }
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE ${RECIPE_TABLE_NAME} ADD COLUMN calories INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE ${RECIPE_TABLE_NAME} ADD COLUMN proteins INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE ${RECIPE_TABLE_NAME} ADD COLUMN fats INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE ${RECIPE_TABLE_NAME} ADD COLUMN carbons INTEGER NOT NULL DEFAULT 0")
            }
        }
        return Room.databaseBuilder(context, RecipeDatabase::class.java, DB_NAME)
            .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeDAO(recipeDatabase: RecipeDatabase) = recipeDatabase.recipeDao()
}