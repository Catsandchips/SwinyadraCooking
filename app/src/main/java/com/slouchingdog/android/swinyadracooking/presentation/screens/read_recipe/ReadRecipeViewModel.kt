package com.slouchingdog.android.swinyadracooking.presentation.screens.read_recipe

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slouchingdog.android.swinyadracooking.domain.entities.CookingStepEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.IngredientEntity
import com.slouchingdog.android.swinyadracooking.domain.use_cases.DeleteRecipeUseCase
import com.slouchingdog.android.swinyadracooking.domain.use_cases.GetRecipeByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ReadRecipeViewModel.ReadRecipeViewModelFactory::class)
class ReadRecipeViewModel @AssistedInject constructor(
    @Assisted val id: String,
    val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    val deleteRecipeUseCase: DeleteRecipeUseCase
) : ViewModel() {
    @AssistedFactory
    interface ReadRecipeViewModelFactory {
        fun create(id: String): ReadRecipeViewModel
    }

    private val _readRecipeState: MutableStateFlow<ReadRecipeState> =
        MutableStateFlow(ReadRecipeState())
    val readRecipeState = _readRecipeState.asStateFlow()

    init {
        viewModelScope.launch {
            getRecipeByIdUseCase(id).collect { recipe ->
                if (recipe != null) {
                    _readRecipeState.update {
                        it.copy(
                            recipeId = id,
                            dishName = recipe.recipeEntity.name,
                            dishType = recipe.recipeEntity.dishType,
                            cookingTime = recipe.recipeEntity.cookingTime,
                            imageUri = recipe.recipeEntity.imageUri?.toUri(),
                            calories = recipe.recipeEntity.calories,
                            proteins = recipe.recipeEntity.proteins,
                            fats = recipe.recipeEntity.fats,
                            carbons = recipe.recipeEntity.carbons,
                            ingredients = recipe.ingredients,
                            cookingSteps = recipe.cookingSteps
                        )
                    }
                }
            }
        }
    }

    fun onDeleteButtonClick(id: String) {
        viewModelScope.launch {
            deleteRecipeUseCase(id)
        }
    }
}

data class ReadRecipeState(
    val recipeId: String = "",
    val dishName: String = "",
    val dishType: Int = 0,
    val cookingTime: Int = 0,
    val imageUri: Uri? = null,
    val calories: Double = 0.0,
    val proteins: Double = 0.0,
    val fats: Double = 0.0,
    val carbons: Double = 0.0,
    val ingredients: List<IngredientEntity> = emptyList(),
    val cookingSteps: List<CookingStepEntity> = emptyList()
)