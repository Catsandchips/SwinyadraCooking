package com.slouchingdog.android.swinyadracooking.presentation.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.AddRecipeScreenState
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.UpdateRecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadRecipeScreen(id: String, onEditButtonClick: (String) -> Unit) {
    val viewModel =
        hiltViewModel<UpdateRecipeViewModel, UpdateRecipeViewModel.UpdateRecipeViewModelFactory> {
            it.create(id)
        }
    val state: AddRecipeScreenState by viewModel.addRecipeScreenState.collectAsState()
    val unitTypes = stringArrayResource(R.array.units)
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(state.dishName) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEditButtonClick(id) }) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = stringResource(R.string.update_recipe_button_description)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(stringResource(R.string.ingredients_title))
            }
            items(state.ingredients) { ingredient ->
                Row {
                    Text(ingredient.name)
                    Text("${ingredient.amount} ${unitTypes[ingredient.unitType]}")
                }
            }
            item {
                Text(stringResource(R.string.cooking_steps_title))
            }
            itemsIndexed(state.cookingSteps) { index, step ->
                Text("${index + 1}. ${step.description}")
            }
        }
    }
}