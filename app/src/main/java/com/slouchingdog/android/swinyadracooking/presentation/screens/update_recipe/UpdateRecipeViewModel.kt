package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slouchingdog.android.swinyadracooking.domain.entities.CookingStepEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.IngredientEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity
import com.slouchingdog.android.swinyadracooking.domain.use_cases.AddRecipeUseCase
import com.slouchingdog.android.swinyadracooking.domain.use_cases.DeleteRecipeUseCase
import com.slouchingdog.android.swinyadracooking.domain.use_cases.GetRecipeByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.util.UUID


@HiltViewModel(assistedFactory = UpdateRecipeViewModel.UpdateRecipeViewModelFactory::class)
class UpdateRecipeViewModel @AssistedInject constructor(
    @ApplicationContext private val context: Context,
    @Assisted val id: String?,
    val addRecipeUseCase: AddRecipeUseCase,
    val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    val deleteRecipeUseCase: DeleteRecipeUseCase
) : ViewModel() {

    @AssistedFactory
    interface UpdateRecipeViewModelFactory {
        fun create(id: String?): UpdateRecipeViewModel
    }

    private val _updateRecipeState: MutableStateFlow<UpdateRecipeScreenState> = MutableStateFlow(
        UpdateRecipeScreenState()
    )
    val updateRecipeScreenState = _updateRecipeState.asStateFlow()

    init {
        if (id != null) {
            viewModelScope.launch {
                val recipe = getRecipeByIdUseCase(id)
                _updateRecipeState.update {
                    it.copy(
                        recipeId = id,
                        dishName = recipe.recipeEntity.name,
                        dishType = recipe.recipeEntity.dishType,
                        cookingTime = recipe.recipeEntity.cookingTime,
                        imageUri = recipe.recipeEntity.imageUri?.toUri(),
                        ingredients = recipe.ingredients,
                        cookingSteps = recipe.cookingSteps
                    )
                }
            }
        }
    }

    fun onDishNameChange(newName: String) {
        _updateRecipeState.update { _updateRecipeState.value.copy(dishName = newName) }
    }

    fun onDishTypeChange(newType: Int) {
        _updateRecipeState.update { _updateRecipeState.value.copy(dishType = newType) }
    }

    fun onDishTypeSelectorExpandedChange() {
        val isExpanded = _updateRecipeState.value.isDishTypeSelectorExpanded
        _updateRecipeState.update { _updateRecipeState.value.copy(isDishTypeSelectorExpanded = !isExpanded) }
    }

    fun onDishTypeDismissTypeRequest() {
        _updateRecipeState.update { _updateRecipeState.value.copy(isDishTypeSelectorExpanded = false) }
    }

    fun onCookingTimeChange(newTime: String) {
        val time = newTime.toIntOrNull()
        _updateRecipeState.update { _updateRecipeState.value.copy(cookingTime = time ?: 0) }
    }

    fun onImageUriChange(newUri: Uri?) {
        _updateRecipeState.update { _updateRecipeState.value.copy(imageUri = newUri) }
    }

    fun onStepAdd() {
        _updateRecipeState.update {
            _updateRecipeState.value.copy(
                cookingSteps = it.cookingSteps + CookingStepEntity(
                    id = UUID.randomUUID().toString(),
                    recipeId = it.recipeId
                )
            )
        }
    }

    fun onStepUpdate(index: Int, updatedDescription: String) {
        _updateRecipeState.update {
            _updateRecipeState.value.copy(
                cookingSteps = it.cookingSteps.toMutableList()
                    .apply { this[index] = this[index].copy(description = updatedDescription) })
        }
    }

    fun onStepRemove(index: Int) {
        if (_updateRecipeState.value.cookingSteps.size > 1) {
            _updateRecipeState.update {
                _updateRecipeState.value.copy(
                    cookingSteps = it.cookingSteps.toMutableList().apply { removeAt(index) })
            }
        }
    }

    fun onIngredientAdd() {
        _updateRecipeState.update {
            _updateRecipeState.value.copy(
                ingredients = it.ingredients + IngredientEntity(
                    id = UUID.randomUUID().toString(),
                    recipeId = it.recipeId
                )
            )
        }
    }

    fun onIngredientRemove(index: Int) {
        if (_updateRecipeState.value.ingredients.size > 1) {
            _updateRecipeState.update {
                _updateRecipeState.value.copy(
                    ingredients = it.ingredients.toMutableList().apply { removeAt(index) })
            }
        }
    }

    fun onIngredientNameChange(index: Int, name: String) {
        _updateRecipeState.update {
            _updateRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply { this[index] = this[index].copy(name = name) })
        }
    }

    fun onIngredientAmountChange(index: Int, amount: Int?) {
        _updateRecipeState.update {
            _updateRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply { this[index] = this[index].copy(amount = amount ?: 0) })
        }
    }

    fun onIngredientUnitTypeChange(index: Int, unitType: Int) {
        _updateRecipeState.update {
            _updateRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply {
                        this[index] =
                            this[index].copy(unitType = unitType, isUnitTypeExpanded = false)
                    })
        }
    }

    fun onIngredientUnitTypeExpandedChange(index: Int) {
        _updateRecipeState.update {
            _updateRecipeState.value.copy(
                ingredients = it.ingredients.toMutableList()
                    .apply {
                        this[index] =
                            this[index].copy(isUnitTypeExpanded = !this[index].isUnitTypeExpanded)
                    })
        }
    }

    fun onSaveButtonClick() {
        val currentState = _updateRecipeState.value
        var finalImageUri: String? = null
        currentState.imageUri?.let {
            finalImageUri = saveImageToInternalStorage(it)
        }
        viewModelScope.launch {
            val recipe = RecipeDetailedEntity(
                recipeEntity = RecipeEntity(
                    id = currentState.recipeId,
                    name = currentState.dishName,
                    dishType = currentState.dishType,
                    cookingTime = currentState.cookingTime,
                    imageUri = finalImageUri
                ),
                ingredients = currentState.ingredients,
                cookingSteps = currentState.cookingSteps
            )
            addRecipeUseCase(recipe)
        }
    }

    fun onDeleteButtonClick(id: String) {
        viewModelScope.launch {
            deleteRecipeUseCase(id)
        }
    }

    private fun saveImageToInternalStorage(uri: Uri): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = "recipe_image_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, fileName)
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            file.toUri().toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

data class UpdateRecipeScreenState(
    val recipeId: String = UUID.randomUUID().toString(),
    val dishName: String = "",
    val dishType: Int = 0,
    val cookingTime: Int = 0,
    val imageUri: Uri? = null,
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