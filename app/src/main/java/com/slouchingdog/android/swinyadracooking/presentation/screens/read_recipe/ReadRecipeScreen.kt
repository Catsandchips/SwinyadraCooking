package com.slouchingdog.android.swinyadracooking.presentation.screens.read_recipe

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list.getDishIcon
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.NumberCircle
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.UpdateRecipeScreenState
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.UpdateRecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadRecipeScreen(id: String, onEditButtonClick: (String) -> Unit, popBackStack: () -> Unit) {
    val viewModel =
        hiltViewModel<UpdateRecipeViewModel, UpdateRecipeViewModel.UpdateRecipeViewModelFactory> {
            it.create(id)
        }
    val state: UpdateRecipeScreenState by viewModel.updateRecipeScreenState.collectAsState()

    val unitTypes = stringArrayResource(R.array.units)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = state.dishName) },
                navigationIcon = {
                    IconButton(onClick = { popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onDeleteButtonClick(id)
                        popBackStack()
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditButtonClick(id) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.White
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
                            .background(color = MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Icon(
                            painter = painterResource(getDishIcon(1)),
                            contentDescription = "Dish photo",
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.Center),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }

                item {
                    Row(modifier = Modifier.padding(start = 16.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.grocery_24px),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.ingredients_title),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary,
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
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.cooking_steps_title),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary,
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


@Composable
fun DotSeparator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .height(1.dp),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val dotSize = 2.dp.toPx()
            val dotSpacing = 3.dp.toPx()
            val width = size.width

            var currentX = 0f
            while (currentX < width) {
                drawCircle(
                    color = Color.Gray,
                    radius = dotSize / 2,
                    center = Offset(currentX, size.height / 2)
                )
                currentX += dotSize + dotSpacing
            }
        }
    }
}
