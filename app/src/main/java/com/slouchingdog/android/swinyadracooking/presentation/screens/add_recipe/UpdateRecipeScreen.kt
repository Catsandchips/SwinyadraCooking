package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.CookingStepsItem
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.DishDescriptionFields
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.IngredientItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateRecipeScreen() {
    val addRecipeViewModel: AddRecipeViewModel = viewModel()
    val state: AddRecipeScreenState by addRecipeViewModel.addRecipeScreenState.collectAsState()
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.add_recipe_screen_title)) },
                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = { focusManager.clearFocus() }
                ))
        {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    DishDescriptionFields(addRecipeViewModel)
                }


                item {
                    Text(stringResource(R.string.ingredients_title))
                }

                itemsIndexed(state.ingredients) { index, ingredient ->
                    IngredientItem(
                        ingredient = ingredient,
                        ingredientIndex = index,
                        ingredientsCount = state.ingredients.size,
                        isExpanded = ingredient.isUnitTypeExpanded,
                        onNameChange = { index, name ->
                            addRecipeViewModel.onIngredientNameChange(
                                index,
                                name
                            )
                        },
                        onAmountChange = { index, amount ->
                            addRecipeViewModel.onAmountChange(index, amount)
                        },
                        onUnitTypeChange = { index, unitType ->
                            addRecipeViewModel.onUnitTypeChange(index, unitType)
                        },
                        onIngredientDelete = { addRecipeViewModel.onIngredientRemove(it) },
                        onUnitTypeExpandedChange = {
                            addRecipeViewModel.onUnitTypeExpandedChange(
                                index
                            )
                        },
                        onDismissRequest = { }
                    )
                }

                item {
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { addRecipeViewModel.onIngredientAdd() }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add ingredient")
                        Text(stringResource(R.string.add_ingredient_button_text))
                    }
                }

                item {
                    Text(stringResource(R.string.cooking_steps_title))
                }

                itemsIndexed(state.cookingSteps) { index, cookingStep ->
                    CookingStepsItem(
                        stepDescription = cookingStep.description,
                        stepIndex = index + 1,
                        stepsCount = state.cookingSteps.size,
                        onStepChange = { addRecipeViewModel.onStepUpdate(index, it) },
                        onDeleteStep = { addRecipeViewModel.onStepRemove(index) })
                }

                item {
                    TextButton(
                        onClick = { addRecipeViewModel.onStepAdd() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add cooking step",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(stringResource(R.string.add_step_button_text))
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = { addRecipeViewModel.onSaveButtonClick() }) {
                            Text(
                                stringResource(R.string.save_button_text)
                            )
                        }
                        Button(onClick = { addRecipeViewModel.onCancelButtonClick() }) {
                            Text(
                                stringResource(R.string.cancel_button_text)
                            )
                        }
                    }
                }
            }
        }
    }


}