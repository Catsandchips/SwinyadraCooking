package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    onNameChange: (Int, String) -> Unit,
    onAmountChange: (Int, Int) -> Unit,
    onUnitTypeChange: (Int, Int) -> Unit,
    onIngredientDelete: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Поле для названия
            OutlinedTextField(
                modifier = Modifier.weight(1.3f),
                value = ingredient.name,
                onValueChange = { onNameChange(ingredientIndex, it) },
                label = { Text("Название") },
                singleLine = true
            )

            // Поле для количества
            OutlinedTextField(
                modifier = Modifier.weight(0.9f),
                value = ingredient.amount.toString(),
                onValueChange = { onAmountChange(ingredientIndex, it.toInt()) },
                label = { Text("Кол-во") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true
            )

            // Выбор единицы измерения
            var expanded = false
            val units = stringArrayResource(R.array.units)

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {},
                modifier = Modifier.weight(0.82f),
            ) {
                TextField(
                    readOnly = true,
                    value = units[ingredient.unitType],
                    onValueChange = {},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) }
                )
                ExposedDropdownMenu(expanded = false, onDismissRequest = { expanded = false }) {
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
                        .size(48.dp)
                        .weight(0.5f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Удалить"
                    )
                }
            }
        }
    }
}