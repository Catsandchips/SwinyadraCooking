package com.slouchingdog.android.swinyadracooking.presentation.screens.read_recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.DotSeparator
import com.slouchingdog.android.swinyadracooking.presentation.NumberCircle
import com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list.components.getDishIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadRecipeScreen(id: String, onEditButtonClick: (String) -> Unit, popBackStack: () -> Unit) {
    val viewModel =
        hiltViewModel<ReadRecipeViewModel, ReadRecipeViewModel.ReadRecipeViewModelFactory> {
            it.create(id)
        }
    val state: ReadRecipeState by viewModel.readRecipeState.collectAsState()
    val unitTypes = stringArrayResource(R.array.units)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = state.dishName) },
                navigationIcon = {
                    IconButton(onClick = { popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back_button_descr)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onDeleteButtonClick(id)
                        popBackStack()
                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_recipe_button_descr)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditButtonClick(id) },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = stringResource(R.string.update_recipe_button_description)
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(top = innerPadding.calculateTopPadding()))
                .clip(RoundedCornerShape(24.dp))
                .background(color = MaterialTheme.colorScheme.background)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(bottom = 16.dp)
                            .background(color = MaterialTheme.colorScheme.primary)
                    ) {
                        if (state.imageUri != null) {
                            AsyncImage(
                                model = state.imageUri,
                                contentDescription = stringResource(R.string.dish_photo),
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(
                                imageVector = getDishIcon(state.dishType),
                                contentDescription = stringResource(R.string.dish_photo),
                                modifier = Modifier
                                    .size(48.dp)
                                    .align(Alignment.Center),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                    }
                }

                item {
                    Row(modifier = Modifier.padding(start = 16.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.grocery_24px),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.ingredients_title),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                        )
                    }
                }
                items(state.ingredients) { ingredient ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, bottom = 8.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(ingredient.name)
                        DotSeparator(modifier = Modifier.weight(1f))
                        Text(
                            "${ingredient.amount} ${unitTypes[ingredient.unitType]}"
                        )
                    }
                }
                item {
                    Row(modifier = Modifier.padding(top = 16.dp, start = 16.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.cooking_24px),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.cooking_steps_title),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                        )
                    }
                }
                itemsIndexed(state.cookingSteps) { index, step ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, bottom = 8.dp),
                    ) {
                        NumberCircle(
                            index + 1,
                            modifier = Modifier
                                .alignByBaseline()
                                .padding(end = 8.dp)

                        )
                        Text(text = step.description, modifier = Modifier.alignByBaseline())
                    }
                }
            }
        }
    }
}



