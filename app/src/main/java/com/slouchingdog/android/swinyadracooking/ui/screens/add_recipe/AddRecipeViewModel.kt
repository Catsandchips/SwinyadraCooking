package com.slouchingdog.android.swinyadracooking.ui.screens.add_recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddRecipeViewModel : ViewModel() {
    private val _addRecipeState: MutableLiveData<AddRecipeScreenState> = MutableLiveData(
        AddRecipeScreenState()
    )
    val addRecipeScreenState: LiveData<AddRecipeScreenState> = _addRecipeState

    fun onDishNameChange(newName: String) {
        _addRecipeState.value = _addRecipeState.value?.copy(dishName = newName)
    }

    fun onDishTypeChange(newType: Int) {
        _addRecipeState.value = _addRecipeState.value?.copy(dishType = newType)
    }

    fun onDishTypeSelectorExpandedChange(){
        val isExpanded = _addRecipeState.value?.isDishTypeSelectorExpanded ?: false
        _addRecipeState.value = _addRecipeState.value?.copy(isDishTypeSelectorExpanded = !isExpanded)
    }

    fun onDismissTypeRequest(){
        _addRecipeState.value = _addRecipeState.value?.copy(isDishTypeSelectorExpanded = false)
    }

    fun onCookingTimeChange(newTime: String) {
        _addRecipeState.value = _addRecipeState.value?.copy(cookingTime = newTime)
    }

    fun onPortionsCountChange(newCount: String) {
        _addRecipeState.value = _addRecipeState.value?.copy(portionsCount = newCount)
    }

    fun onCancelButtonClick(){}
    fun onSaveButtonClick(){}
}

data class AddRecipeScreenState(
    val dishName: String = "",
    val dishType: Int = 0,
    val cookingTime: String = "",
    val portionsCount: String = "",
    val ingredients: List<String> = emptyList(),
    val cookingSteps: List<String> = emptyList(),
    val isDishTypeSelectorExpanded: Boolean = false
)