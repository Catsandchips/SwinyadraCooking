package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.SwinyadraTextField
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.UpdateRecipeScreenState

@Composable
fun CPFCFields(
    state: UpdateRecipeScreenState,
    onCaloriesChange: (String) -> Unit,
    onProteinsChange: (String) -> Unit,
    onFatsChange: (String) -> Unit,
    onCarbonsChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(R.string.nutritional_value_title),
            style = MaterialTheme.typography.titleMedium
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SwinyadraTextField(
                value = if (state.calories != 0.0) state.calories.toString() else "",
                onValueChange = { onCaloriesChange(it) },
                label = { Text(stringResource(R.string.calories_field_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.25f)
            )
            SwinyadraTextField(
                value = if (state.proteins != 0.0) state.proteins.toString() else "",
                onValueChange = { onProteinsChange(it) },
                label = { Text(stringResource(R.string.proteins_field_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.25f)
            )
            SwinyadraTextField(
                value = if (state.fats != 0.0) state.fats.toString() else "",
                onValueChange = { onFatsChange(it) },
                label = { Text(stringResource(R.string.fats_field_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.25f)
            )
            SwinyadraTextField(
                value = if (state.carbons != 0.0) state.carbons.toString() else "",
                onValueChange = { onCarbonsChange(it) },
                label = { Text(stringResource(R.string.carbons_field_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.25f)
            )
        }
    }
}