package com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity

@Composable
@Preview
fun RecipeListScreen(innerPadding: PaddingValues = PaddingValues()) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(testList){recipe ->
            RecipeCard(recipe)
        }
    }
}

val testList = listOf(
    RecipeEntity("Test", "Test 1", 0, 12, 1),
    RecipeEntity("Test", "Test 2", 1, 32, 1),
    RecipeEntity("Test", "Test 3", 2, 1, 1),
    RecipeEntity("Test", "Test 4", 2, 120, 1),
    RecipeEntity("Test", "Test 5", 1, 13, 1),
    RecipeEntity("Test", "Test 3", 2, 1, 1),
    RecipeEntity("Test", "Test 4", 2, 120, 1),
    RecipeEntity("Test", "Test 5", 1, 13, 1)
)