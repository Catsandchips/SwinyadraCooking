package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe

import androidx.lifecycle.ViewModel
import com.slouchingdog.android.swinyadracooking.domain.entities.CookingStepEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.IngredientEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class AddRecipeViewModel : ViewModel() {
    private val _addRecipeState: MutableStateFlow<AddRecipeScreenState> = MutableStateFlow(
        AddRecipeScreenState()
    )
    val addRecipeScreenState = _addRecipeState.asStateFlow()

    fun onDishNameChange(newName: String) {
        _addRecipeState.update { _addRecipeState.value.copy(dishName = newName) }
    }

    fun onDishTypeChange(newType: Int) {
        _addRecipeState.update { _addRecipeState.value.copy(dishType = newType) }
    }

    fun onDishTypeSelectorExpandedChange() {
        val isExpanded = _addRecipeState.value.isDishTypeSelectorExpanded
        _addRecipeState.update { _addRecipeState.value.copy(isDishTypeSelectorExpanded = !isExpanded) }
    }

    fun onDismissTypeRequest() {
        _addRecipeState.update { _addRecipeState.value.copy(isDishTypeSelectorExpanded = false) }
    }

    fun onCookingTimeChange(newTime: String) {
        _addRecipeState.update { _addRecipeState.value.copy(cookingTime = newTime) }
    }

    fun onPortionsCountChange(newCount: String) {
        _addRecipeState.update { _addRecipeState.value.copy(portionsCount = newCount) }
    }

    fun onStepAdd() {
        _addRecipeState.update {
            _addRecipeState.value.copy(
                cookingSteps = it.cookingSteps + CookingStepEntity(
                    id = UUID.randomUUID().toString(),
                    recipeId = it.recipeId
                )
            )
        }
    }

    fun onStepUpdate(index: Int, updatedDescription: String) {
        _addRecipeState.update {
            _addRecipeState.value.copy(
                cookingSteps = it.cookingSteps.toMutableList()
                    .apply { this[index] = this[index].copy(description = updatedDescription) })
        }
    }

    fun onStepRemove(index: Int) {
        if (_addRecipeState.value.cookingSteps.size > 1) {
            _addRecipeState.update {
                _addRecipeState.value.copy(
                    cookingSteps = it.cookingSteps.toMutableList().apply { removeAt(index) })
            }
        }
    }

    fun onIngredientAdd() {
        _addRecipeState.update {
            _addRecipeState.value.copy(
                ingredients = it.ingredients + IngredientEntity(
                    id = UUID.randomUUID().toString(),
                    recipeId = it.recipeId
                )
            )
        }
    }

    fun onIngredientRemove(index: Int) {
        if (_addRecipeState.value.ingredients.size > 1) {
            _addRecipeState.update {
                _addRecipeState.value.copy(
                    ingredients = it.ingredients.toMutableList().apply { removeAt(index) })
            }
        }
    }

    fun onIngredientNameChange(index: Int, name: String) {
        _addRecipeState.update {
            _addRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply { this[index] = this[index].copy(name = name) })
        }
    }

    fun onAmountChange(index: Int, amount: Int?) {
        _addRecipeState.update {
            _addRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply { this[index] = this[index].copy(amount = amount ?: 0) })
        }
    }

    fun onUnitTypeChange(index: Int, unitType: Int) {
        _addRecipeState.update {
            _addRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply { this[index] = this[index].copy(unitType = unitType, isUnitTypeExpanded = false) })
        }
    }

    fun onUnitTypeExpandedChange(index: Int) {
        _addRecipeState.update {
            _addRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply { this[index] = this[index].copy(isUnitTypeExpanded = !this[index].isUnitTypeExpanded) })
        }
    }

    fun onCancelButtonClick() {}
    fun onSaveButtonClick() {}
}

data class AddRecipeScreenState(
    val recipeId: String = UUID.randomUUID().toString(),
    val dishName: String = "",
    val dishType: Int = 0,
    val cookingTime: String = "",
    val portionsCount: String = "",
    val ingredients: List<IngredientEntity> = listOf(
        IngredientEntity(
            id = UUID.randomUUID().toString(),
            recipeId = recipeId
        )
    ),
    val cookingSteps: List<CookingStepEntity> = listOf(
        CookingStepEntity(
            id = UUID.randomUUID().toString(),
            recipeId = recipeId
        )
    ),
    val isDishTypeSelectorExpanded: Boolean = false
)