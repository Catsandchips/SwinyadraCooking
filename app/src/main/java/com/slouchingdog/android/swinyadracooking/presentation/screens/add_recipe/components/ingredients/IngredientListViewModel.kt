package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IngredientListViewModel : ViewModel() {
    private val _ingredientList = MutableStateFlow(listOf(Ingredient()))
    val ingredientList = _ingredientList.asStateFlow()


    fun onIngredientAdd() {
        _ingredientList.update { it + Ingredient() }
    }

    fun onIngredientRemove(index: Int) {
        if (_ingredientList.value.size > 1) {
            _ingredientList.update { it.toMutableList().apply { removeAt(index) } }
        }
    }

    fun onIngredientNameChange(index: Int, name: String) {
        _ingredientList.update {
            it.toMutableList().apply { this[index] = this[index].copy(name = name) }
        }
    }

    fun onAmountChange(index: Int, amount: Int?) {
        _ingredientList.update {
            it.toMutableList().apply { this[index] = this[index].copy(amount = amount ?: 0) }
        }
    }

    fun onUnitTypeChange(index: Int, unitType: Int) {
        _ingredientList.update {
            it.toMutableList().apply {
                this[index] = this[index].copy(unitType = unitType, isUnitTypeExpanded = false)
            }
        }
    }

    fun onUnitTypeExpandedChange(index: Int) {
        _ingredientList.update {
            it.toMutableList().apply {
                this[index] = this[index].copy(isUnitTypeExpanded = !this[index].isUnitTypeExpanded)
            }
        }
    }

    fun onDismissRequest(index: Int) {
        _ingredientList.update {
            it.toMutableList().apply {
                this[index] = this[index].copy(isUnitTypeExpanded = !this[index].isUnitTypeExpanded)
            }
        }
    }
}