package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.cooking_steps

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CookingStepsViewModel : ViewModel() {
    private val _cookingSteps = MutableStateFlow(listOf(CookingStep()))
    val cookingSteps: StateFlow<List<CookingStep>> = _cookingSteps.asStateFlow()

    fun onStepAdd() {
        _cookingSteps.update { it + CookingStep() }
    }

    fun onStepUpdate(index: Int, updatedDescription: String) {
        _cookingSteps.update { steps ->
            steps.toMutableList().apply {
                this[index] = this[index].copy(stepDescription = updatedDescription)
            }
        }
    }

    fun onStepRemove(index: Int) {
        if (_cookingSteps.value.size > 1) {
            _cookingSteps.update { ingredients ->
                ingredients.toMutableList().apply { removeAt(index) }
            }
        }
    }

    fun getValidSteps(): List<CookingStep> {
        return _cookingSteps.value.filter { it.stepDescription.isNotBlank() }
    }
}