package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components.CookingStepsItem
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components.DishDescriptionFields
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components.IngredientItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateRecipeScreen(id: String?, onRecipeSave: () -> Unit) {
    val updateRecipeViewModel =
        hiltViewModel<UpdateRecipeViewModel, UpdateRecipeViewModel.UpdateRecipeViewModelFactory> {
            it.create(id)
        }
    val state: AddRecipeScreenState by updateRecipeViewModel.addRecipeScreenState.collectAsState()
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (id == null) stringResource(R.string.add_recipe_screen_title) else state.dishName,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState()),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }, containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(top = innerPadding.calculateTopPadding()))
                .clip(RoundedCornerShape(24.dp))
                .background(color = MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = { focusManager.clearFocus() })
        ) {
            LazyColumn(
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    DishDescriptionFields(updateRecipeViewModel)
                }

                item {
                    Text(
                        text = stringResource(R.string.ingredients_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.tertiary,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        state.ingredients.forEachIndexed { index, ingredient ->
                            IngredientItem(
                                ingredient = ingredient,
                                ingredientIndex = index,
                                ingredientsCount = state.ingredients.size,
                                isExpanded = ingredient.isUnitTypeExpanded,
                                onNameChange = { index, name ->
                                    updateRecipeViewModel.onIngredientNameChange(
                                        index, name
                                    )
                                },
                                onAmountChange = { index, amount ->
                                    updateRecipeViewModel.onAmountChange(index, amount)
                                },
                                onUnitTypeChange = { index, unitType ->
                                    updateRecipeViewModel.onUnitTypeChange(index, unitType)
                                },
                                onIngredientDelete = { updateRecipeViewModel.onIngredientRemove(it) },
                                onUnitTypeExpandedChange = {
                                    updateRecipeViewModel.onUnitTypeExpandedChange(
                                        index
                                    )
                                },
                                onDismissRequest = { })
                        }

                        TextButton(
                            onClick = { updateRecipeViewModel.onIngredientAdd() },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                modifier = Modifier.padding(end = 8.dp),
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "Add ingredient"
                            )
                            Text(stringResource(R.string.add_ingredient_button_text))
                        }
                    }

                }

                item {
                    Text(
                        text = stringResource(R.string.cooking_steps_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .border(
                                2.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(12.dp)
                            )
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        state.cookingSteps.forEachIndexed { index, cookingStep ->
                            CookingStepsItem(
                                stepDescription = cookingStep.description,
                                stepIndex = index + 1,
                                stepsCount = state.cookingSteps.size,
                                onStepChange = { updateRecipeViewModel.onStepUpdate(index, it) },
                                onDeleteStep = { updateRecipeViewModel.onStepRemove(index) })
                        }
                        TextButton(
                            onClick = { updateRecipeViewModel.onStepAdd() },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                Icons.Default.AddCircle,
                                contentDescription = "Add cooking step",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(stringResource(R.string.add_step_button_text))
                        }
                    }
                }

                item {
                    GradientButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            updateRecipeViewModel.onSaveButtonClick()
                            onRecipeSave()
                        })
                    {
                        Text(stringResource(R.string.save_button_text))
                    }
                }
            }
        }
    }
}