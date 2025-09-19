package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.ui.screens.add_recipe.components.DishTypeDropdownMenu

@Composable
@Preview
fun AddRecipeScreen(innerPadding: PaddingValues = PaddingValues()) {
    val viewModel: AddRecipeViewModel = viewModel()
    val screenState by viewModel.addRecipeScreenState.observeAsState(AddRecipeScreenState())


    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = screenState.dishName,
            onValueChange = { viewModel.onDishNameChange(it) },
            label = { Text(stringResource(R.string.dish_name_label)) },
            modifier = Modifier
                .fillMaxWidth()
        )

        DishTypeDropdownMenu(
            dishType = screenState.dishType,
            isExpanded = screenState.isDishTypeSelectorExpanded,
            onExpandedChange = { viewModel.onDishTypeSelectorExpandedChange() },
            onTypeSelection = { type -> viewModel.onDishTypeChange(type) },
            onDismissRequest = { viewModel.onDismissTypeRequest() }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.cooking_time_label)) },
            value = screenState.cookingTime,
            onValueChange = { viewModel.onCookingTimeChange(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.portions_count_label)) },
            value = screenState.portionsCount,
            onValueChange = {
                viewModel.onPortionsCountChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { viewModel.onSaveButtonClick() }) { Text(stringResource(R.string.save_button_text)) }
            Button(onClick = { viewModel.onCancelButtonClick() }) { Text(stringResource(R.string.cancel_button_text)) }
        }

    }
}