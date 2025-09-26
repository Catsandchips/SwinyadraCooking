package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.DishDescriptionFields
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.cooking_steps.CookingStepsItem
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.cooking_steps.CookingStepsViewModel
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients.IngredientItem
import com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients.IngredientListViewModel

@Composable
fun AddRecipeScreen(innerPadding: PaddingValues = PaddingValues()) {
    val addRecipeViewModel: AddRecipeViewModel = viewModel()
    val screenState by addRecipeViewModel.addRecipeScreenState.observeAsState(AddRecipeScreenState())
    val ingredientListViewModel: IngredientListViewModel = viewModel()
    val ingredients by ingredientListViewModel.ingredientList.collectAsState()
    val cookingStepsViewModel: CookingStepsViewModel = viewModel()
    val cookingSteps by cookingStepsViewModel.cookingSteps.collectAsState()
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
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

            items(ingredients) { ingredient ->
                val index = ingredients.indexOf(ingredient)
                IngredientItem(
                    ingredient = ingredient,
                    ingredientIndex = index,
                    ingredientsCount = ingredients.size,
                    isExpanded = ingredient.isUnitTypeExpanded,
                    onNameChange = { index, name ->
                        ingredientListViewModel.onIngredientNameChange(
                            index,
                            name
                        )
                    },
                    onAmountChange = { index, amount ->
                        ingredientListViewModel.onAmountChange(index, amount)
                    },
                    onUnitTypeChange = { index, unitType ->
                        ingredientListViewModel.onUnitTypeChange(index, unitType)
                    },
                    onIngredientDelete = { ingredientListViewModel.onIngredientRemove(it) },
                    onUnitTypeExpandedChange = {
                        ingredientListViewModel.onUnitTypeExpandedChange(
                            index
                        )
                    },
                    onDismissRequest = { ingredientListViewModel.onDismissRequest(index) }
                )
            }

            item {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { ingredientListViewModel.onIngredientAdd() }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add ingredient")
                    Text(stringResource(R.string.add_ingredient_button_text))
                }
            }

            item {
                Text(stringResource(R.string.cooking_steps_title))
            }

            items(cookingSteps) { cookingStep ->
                CookingStepsItem(
                    stepDescription = cookingStep.stepDescription,
                    stepIndex = cookingSteps.indexOf(cookingStep) + 1,
                    stepsCount = cookingSteps.size,
                    onStepChange = {
                        cookingStepsViewModel.onStepUpdate(
                            cookingSteps.indexOf(cookingStep),
                            it
                        )
                    },
                    onDeleteStep = {
                        cookingStepsViewModel.onStepRemove(
                            cookingSteps.indexOf(
                                cookingStep
                            )
                        )
                    })
            }

            item {
                TextButton(
                    onClick = { cookingStepsViewModel.onStepAdd() },
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