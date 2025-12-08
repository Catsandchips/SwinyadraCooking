package com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity
import com.slouchingdog.android.swinyadracooking.domain.use_cases.GetRecipeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(val getRecipeListUseCase: GetRecipeListUseCase) :
    ViewModel() {
    private val _recipeListState: MutableStateFlow<RecipeListScreenState> = MutableStateFlow(
        RecipeListScreenState()
    )
    val recipeListScreenState = _recipeListState.asStateFlow()

    init {
        viewModelScope.launch {
            getRecipeListUseCase().collect { recipes ->
                _recipeListState.update {
                    it.copy(
                        baseRecipes = recipes,
                        searchedRecipes = recipes
                    )
                }
            }
        }
    }

    fun onSearchBarExpandedChange() {
        _recipeListState.update { it.copy(isSearchBarOpened = !it.isSearchBarOpened) }
    }

    fun onQueryChange(query: String) {
        _recipeListState.update { it.copy(query = query) }
        filterRecipeList()
    }

    private fun filterRecipeList() {
        _recipeListState.update { state ->
            state.copy(searchedRecipes = state.baseRecipes.filter { recipe ->
                recipe.recipeEntity.name.startsWith(
                    prefix = state.query,
                    ignoreCase = true
                )
            })
        }
    }
}

data class RecipeListScreenState(
    val baseRecipes: List<RecipeDetailedEntity> = listOf(),
    val searchedRecipes: List<RecipeDetailedEntity> = listOf(),
    val isSearchBarOpened: Boolean = false,
    val query: String = ""
)