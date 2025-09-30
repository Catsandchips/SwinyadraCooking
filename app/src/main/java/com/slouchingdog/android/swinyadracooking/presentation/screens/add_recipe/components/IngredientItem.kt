package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientItem(
    ingredient: Ingredient = Ingredient(),
    ingredientIndex: Int,
    ingredientsCount: Int,
    isExpanded: Boolean,
    onNameChange: (Int, String) -> Unit,
    onAmountChange: (Int, Int?) -> Unit,
    onUnitTypeChange: (Int, Int) -> Unit,
    onIngredientDelete: (Int) -> Unit,
    onUnitTypeExpandedChange: () -> Unit,
    onDismissRequest: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(2f),
            value = ingredient.name,
            onValueChange = { onNameChange(ingredientIndex, it) },
            label = { Text("Название") },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = ingredient.amount.toString(),
            onValueChange = { onAmountChange(ingredientIndex, it.toIntOrNull()) },
            label = { Text("Кол-во") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true
        )

        val units = stringArrayResource(R.array.units)

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { onUnitTypeExpandedChange() },
            modifier = Modifier.weight(1.2f),
        ) {
            TextField(
                readOnly = true,
                modifier = Modifier
                    .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                    .fillMaxWidth(),
                value = units[ingredient.unitType],
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { onDismissRequest() }) {
                units.forEach { selectedUnit ->
                    DropdownMenuItem(
                        text = { Text(selectedUnit) },
                        onClick = {
                            onUnitTypeChange(
                                ingredientIndex,
                                units.indexOf(selectedUnit)
                            )
                        }
                    )
                }
            }
        }

        if (ingredientsCount > 1) {
            IconButton(
                onClick = { onIngredientDelete(ingredientIndex) },
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Delete ingredient"
                )
            }
        }
    }
}
