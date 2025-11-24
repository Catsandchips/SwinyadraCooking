package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dining
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.SwinyadraTextField
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.UpdateRecipeViewModel

@Composable
fun DishDescriptionFields(updateRecipeViewModel: UpdateRecipeViewModel) {
    val screenState by updateRecipeViewModel.updateRecipeScreenState.collectAsState()
    val errorsState by updateRecipeViewModel.errorsState.collectAsState()

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(stringResource(R.string.dish_name_label), style = MaterialTheme.typography.titleMedium)
        SwinyadraTextField(
            value = screenState.dishName,
            onValueChange = { updateRecipeViewModel.onDishNameChange(it) },
            placeholder = { Text("Введите название") },
            suffix = {
                Icon(
                    imageVector = Icons.Outlined.Dining,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            modifier = Modifier.fillMaxWidth(),
            isError = errorsState.nameHasError,
            supportingText = stringResource(R.string.fill_field_error)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.dish_type_title),
                    style = MaterialTheme.typography.titleMedium
                )
                DishTypeDropdownMenu(
                    dishType = screenState.dishType,
                    isExpanded = screenState.isDishTypeSelectorExpanded,
                    onExpandedChange = { updateRecipeViewModel.onDishTypeSelectorExpandedChange() },
                    onTypeSelection = { type -> updateRecipeViewModel.onDishTypeChange(type) },
                    onDismissRequest = { updateRecipeViewModel.onDishTypeDismissTypeRequest() },
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.cooking_time_label),
                    style = MaterialTheme.typography.titleMedium
                )
                SwinyadraTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Минуты") },
                    value = if (screenState.cookingTime != 0) screenState.cookingTime.toString() else "",
                    onValueChange = { updateRecipeViewModel.onCookingTimeChange(it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    suffix = {
                        Icon(
                            imageVector = Icons.Outlined.Timer,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    },
                    isError = errorsState.cookingTimeHasError,
                    supportingText = stringResource(R.string.fill_field_error)
                )
            }
        }
    }
}