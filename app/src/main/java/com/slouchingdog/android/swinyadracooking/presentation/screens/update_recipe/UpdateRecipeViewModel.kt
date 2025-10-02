package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.slouchingdog.android.swinyadracooking.domain.entities.CookingStepEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.IngredientEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity
import com.slouchingdog.android.swinyadracooking.domain.use_cases.AddRecipeUseCase
import com.slouchingdog.android.swinyadracooking.domain.use_cases.GetRecipeByIdUseCase
import com.squareup.inject.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject




@HiltViewModel
class UpdateRecipeViewModel @AssistedInject constructor(
    @Assisted val id: String?,
    val addRecipeUseCase: AddRecipeUseCase,
    val getRecipeByIdUseCase: GetRecipeByIdUseCase
) : ViewModel() {

    @AssistedFactory
    interface UpdateRecipeViewModelFactory {
        fun create(id: String?): UpdateRecipeViewModel
    }

    private val _addRecipeState: MutableStateFlow<AddRecipeScreenState> = MutableStateFlow(
        AddRecipeScreenState()
    )
    val addRecipeScreenState = _addRecipeState.asStateFlow()

    init {
        if (id != null) {
            viewModelScope.launch {
                val recipe = getRecipeByIdUseCase(id)
                _addRecipeState.update {
                    it.copy(
                        recipeId = id,
                        dishName = recipe.recipeEntity.name,
                        dishType = recipe.recipeEntity.dishType,
                        cookingTime = recipe.recipeEntity.cookingTime,
                        portionsCount = recipe.recipeEntity.portionsCount,
                        ingredients = recipe.ingredients,
                        cookingSteps = recipe.cookingSteps
                    )
                }
            }
        }
    }

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
        val time = newTime.toIntOrNull()
        _addRecipeState.update { _addRecipeState.value.copy(cookingTime = time ?: 0) }
    }

    fun onPortionsCountChange(newCount: String) {
        val count = newCount.toIntOrNull()
        _addRecipeState.update { _addRecipeState.value.copy(portionsCount = count ?: 0) }
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
                    .apply {
                        this[index] =
                            this[index].copy(unitType = unitType, isUnitTypeExpanded = false)
                    })
        }
    }

    fun onUnitTypeExpandedChange(index: Int) {
        _addRecipeState.update {
            _addRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply {
                        this[index] =
                            this[index].copy(isUnitTypeExpanded = !this[index].isUnitTypeExpanded)
                    })
        }
    }

    fun onCancelButtonClick() {}
    fun onSaveButtonClick() {
        viewModelScope.launch {
            val recipe = RecipeDetailedEntity(
                recipeEntity = RecipeEntity(
                    id = _addRecipeState.value.recipeId,
                    name = _addRecipeState.value.dishName,
                    dishType = _addRecipeState.value.dishType,
                    cookingTime = _addRecipeState.value.cookingTime,
                    portionsCount = _addRecipeState.value.portionsCount
                ),
                ingredients = _addRecipeState.value.ingredients,
                cookingSteps = _addRecipeState.value.cookingSteps
            )
            addRecipeUseCase(recipe)
        }
    }
}

data class AddRecipeScreenState(
    val recipeId: String = UUID.randomUUID().toString(),
    val dishName: String = "",
    val dishType: Int = 0,
    val cookingTime: Int = 0,
    val portionsCount: Int = 0,
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