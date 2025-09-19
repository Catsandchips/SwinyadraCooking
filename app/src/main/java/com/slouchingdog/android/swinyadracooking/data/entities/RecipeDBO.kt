package com.slouchingdog.android.swinyadracooking.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val RECIPE_TABLE_NAME = "recipes"

@Entity(tableName = RECIPE_TABLE_NAME)
data class RecipeDBO (
    @PrimaryKey
    val id: Int,
    val name: String,
    val cookingTime: Int,
    val portionsCount: Int
)

