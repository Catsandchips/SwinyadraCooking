package com.slouchingdog.android.swinyadracooking.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.slouchingdog.android.swinyadracooking.data.entities.INGREDIENT_TABLE_NAME
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
        val MIGRATION_4_5 = object : Migration(4,5){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME ADD COLUMN calories_temp REAL NOT NULL DEFAULT 0.0")
                db.execSQL("UPDATE $RECIPE_TABLE_NAME SET calories_temp = CAST(calories AS REAL)")
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME DROP COLUMN calories")
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME RENAME COLUMN calories_temp TO calories")

                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME ADD COLUMN proteins_temp REAL NOT NULL DEFAULT 0.0")
                db.execSQL("UPDATE $RECIPE_TABLE_NAME SET proteins_temp = CAST(proteins AS REAL)")
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME DROP COLUMN proteins")
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME RENAME COLUMN proteins_temp TO proteins")

                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME ADD COLUMN fats_temp REAL NOT NULL DEFAULT 0.0")
                db.execSQL("UPDATE $RECIPE_TABLE_NAME SET fats_temp = CAST(fats AS REAL)")
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME DROP COLUMN fats")
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME RENAME COLUMN fats_temp TO fats")

                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME ADD COLUMN carbons_temp REAL NOT NULL DEFAULT 0.0")
                db.execSQL("UPDATE $RECIPE_TABLE_NAME SET carbons_temp = CAST(carbons AS REAL)")
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME DROP COLUMN carbons")
                db.execSQL("ALTER TABLE $RECIPE_TABLE_NAME RENAME COLUMN carbons_temp TO carbons")

                db.execSQL("ALTER TABLE $INGREDIENT_TABLE_NAME ADD COLUMN amount_temp REAL NOT NULL DEFAULT 0.0")
                db.execSQL("UPDATE $INGREDIENT_TABLE_NAME SET amount_temp = CAST(amount AS REAL)")
                db.execSQL("ALTER TABLE $INGREDIENT_TABLE_NAME DROP COLUMN amount")
                db.execSQL("ALTER TABLE $INGREDIENT_TABLE_NAME RENAME COLUMN amount_temp TO amount")
            }
        }
        return Room.databaseBuilder(context, RecipeDatabase::class.java, DB_NAME)
            .addMigrations(MIGRATION_4_5)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeDAO(recipeDatabase: RecipeDatabase) = recipeDatabase.recipeDao()
}