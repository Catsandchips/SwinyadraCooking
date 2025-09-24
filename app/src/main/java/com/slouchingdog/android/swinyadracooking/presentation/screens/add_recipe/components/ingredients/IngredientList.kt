package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.ingredients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.slouchingdog.android.swinyadracooking.R

@Composable
fun IngredientList() {
    val viewModel: IngredientListViewModel = viewModel()
    val ingredients by viewModel.ingredients.collectAsState()

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(stringResource(R.string.ingredients_title))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(ingredients) { ingredient ->
                val index = ingredients.indexOf(ingredient)
                IngredientItem(
                    ingredient = ingredient,
                    ingredientIndex = index,
                    ingredientsCount = ingredients.size,
                    onNameChange = { index, name -> viewModel.onIngredientNameChange(index, name) },
                    onAmountChange = { index, amount ->
                        viewModel.onIngredientAmountChange(index, amount)
                    },
                    onUnitTypeChange = { index, unitType ->
                        viewModel.onIngredientUnitTypeChange(index, unitType)
                    },
                    onIngredientDelete = { viewModel.onIngredientRemove(it) }
                )
            }
        }

        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onIngredientAdd() }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add ingredient")
            Text(stringResource(R.string.add_ingredient_button_text))
        }
    }
}