package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class IngredientListViewModel : ViewModel() {
    private val _ingredients = mutableStateListOf<Ingredient>()
    val ingredients: List<Ingredient> get() = _ingredients

    private var nextId = 1

    init {
        // Добавляем первое пустое поле
        addIngredient()
    }

    fun addIngredient() {
        _ingredients.add(Ingredient(id = nextId++))
    }

    fun removeIngredient(id: Int) {
        if (_ingredients.size > 1) {
            _ingredients.removeAll { it.id == id }
        }
    }

    fun updateIngredientName(id: Int, name: String) {
        _ingredients.find { it.id == id }?.copy(name = name)
    }

    fun updateIngredientAmount(id: Int, amount: Int) {
        _ingredients.find { it.id == id }?.copy(amount = amount)
    }

    fun updateIngredientUnit(id: Int, unit: Int) {
        _ingredients.find { it.id == id }?.copy(unitType = unit)
    }
}