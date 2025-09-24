package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IngredientListViewModel : ViewModel() {
    private val _ingredients = MutableStateFlow(listOf(Ingredient()))
    val ingredients: StateFlow<List<Ingredient>> get() = _ingredients.asStateFlow()

    fun onIngredientAdd() {
        _ingredients.update { it + Ingredient() }
    }

    fun onIngredientRemove(index: Int) {
        if (_ingredients.value.size > 1) {
            _ingredients.update { ingredients ->
                ingredients.toMutableList().apply { removeAt(index) }
            }
        }
    }

    fun onIngredientNameChange(index: Int, name: String) {
        _ingredients.update {
            it.toMutableList().apply { this[index] = this[index].copy(name = name) }
        }
    }

    fun onIngredientAmountChange(index: Int, amount: Int) {
        _ingredients.update {
            it.toMutableList().apply { this[index] = this[index].copy(amount = amount) }
        }
    }

    fun onIngredientUnitTypeChange(index: Int, unitType: Int) {
        _ingredients.update {
            it.toMutableList().apply { this[index] = this[index].copy(unitType = unitType) }
        }
    }
}