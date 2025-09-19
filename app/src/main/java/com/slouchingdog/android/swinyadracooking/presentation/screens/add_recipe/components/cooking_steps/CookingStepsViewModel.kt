package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.cooking_steps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CookingStepsViewModel: ViewModel() {
    private val _cookingStepList: MutableLiveData<List<CookingStep>> = MutableLiveData(emptyList())
    val cookingStepList: LiveData<List<CookingStep>> = _cookingStepList

}