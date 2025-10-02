package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.AddRecipeScreenState
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.UpdateRecipeViewModel

@Composable
fun DishDescriptionFields(updateRecipeViewModel: UpdateRecipeViewModel) {
    val screenState by updateRecipeViewModel.addRecipeScreenState.collectAsState(
        AddRecipeScreenState()
    )

    Column {
        OutlinedTextField(
            value = screenState.dishName,
            onValueChange = { updateRecipeViewModel.onDishNameChange(it) },
            label = { Text(stringResource(R.string.dish_name_label)) },
            modifier = Modifier
                .fillMaxWidth()
        )
        DishTypeDropdownMenu(
            dishType = screenState.dishType,
            isExpanded = screenState.isDishTypeSelectorExpanded,
            onExpandedChange = { updateRecipeViewModel.onDishTypeSelectorExpandedChange() },
            onTypeSelection = { type -> updateRecipeViewModel.onDishTypeChange(type) },
            onDismissRequest = { updateRecipeViewModel.onDismissTypeRequest() }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.cooking_time_label)) },
            value = screenState.cookingTime.toString(),
            onValueChange = { updateRecipeViewModel.onCookingTimeChange(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.portions_count_label)) },
            value = screenState.portionsCount.toString(),
            onValueChange = {
                updateRecipeViewModel.onPortionsCountChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}