package com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list.components.RecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(onRecipeClick: (String) -> Unit, onAddRecipeClick: () -> Unit) {
    val viewModel: RecipeListViewModel = hiltViewModel()
    val state: RecipeListScreenState by viewModel.recipeListScreenState.collectAsState()

    Scaffold(
        topBar = {
            if (state.isSearchBarOpened) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    inputField = {
                        SearchBarDefaults.InputField(
                            query = state.query,
                            onQueryChange = { viewModel.onQueryChange(it) },
                            onSearch = {},
                            expanded = true,
                            onExpandedChange = {},
                            leadingIcon = {
                                IconButton(onClick = { viewModel.onSearchBarExpandedChange() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                        contentDescription = stringResource(R.string.back_button_descr)
                                    )
                                }
                            },
                            placeholder = { Text(stringResource(R.string.search_field_placeholder)) }
                        )
                    },
                    expanded = false,
                    onExpandedChange = { viewModel.onSearchBarExpandedChange() },
                ) { }
            } else {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(R.string.recipe_list_screen_title)) },
                    actions = {
                        IconButton(
                            onClick = { viewModel.onSearchBarExpandedChange() },
                            content = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = stringResource(R.string.search_button_descr)
                                )
                            }
                        )
                    })
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddRecipeClick() },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_recipe_button_description)
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.searchedRecipes) { recipe ->
                RecipeCard(recipeDetailedEntity = recipe, onCardClick = { onRecipeClick(it) })
            }
        }
    }
}