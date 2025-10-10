package com.slouchingdog.android.swinyadracooking.presentation.screens.read_recipe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.domain.entities.CookingStepEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.IngredientEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ReadRecipeScreen(id: String = "", onEditButtonClick: (String) -> Unit = {}) {
//    val viewModel =
//        hiltViewModel<UpdateRecipeViewModel, UpdateRecipeViewModel.UpdateRecipeViewModelFactory> {
//            it.create(id)
//        }
//    val state: AddRecipeScreenState by viewModel.addRecipeScreenState.collectAsState()
    val state = testData

    val unitTypes = stringArrayResource(R.array.units)
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(state.recipeEntity.name) }) },
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
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            item {
                Text(
                    text = stringResource(R.string.ingredients_title),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
            items(state.ingredients) { ingredient ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(ingredient.name)
                    DotSeparator(modifier = Modifier.weight(1f))
                    Text(
                        "${ingredient.amount} ${unitTypes[ingredient.unitType]}",
                        textAlign = TextAlign.End
                    )
                }
            }
            item {
                Text(
                    text = stringResource(R.string.cooking_steps_title),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
            itemsIndexed(state.cookingSteps) { index, step ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    Text("${index + 1}.", modifier = Modifier.padding(end = 8.dp))
                    Text(step.description)
                }

            }
        }
    }
}

val testData = RecipeDetailedEntity(
    recipeEntity = RecipeEntity(
        id = "",
        name = "Жаркое из курицы",
        dishType = 1,
        cookingTime = 60,
        portionsCount = 4
    ),
    ingredients = listOf(
        IngredientEntity(id = "", recipeId = "", name = "Курица", amount = 100, unitType = 2),
        IngredientEntity(id = "", recipeId = "", name = "Картофель", amount = 1, unitType = 3),
        IngredientEntity(
            id = "",
            recipeId = "",
            name = "Болгарский перец",
            amount = 5,
            unitType = 0
        )
    ),
    cookingSteps = listOf(
        CookingStepEntity(
            id = "",
            recipeId = "",
            description = "Порезать ингредиенты в труху ингредиенты в труху ингредиенты в труху ингредиенты в труху ингредиенты в труху"
        ),
        CookingStepEntity(id = "", recipeId = "", description = "Закинуть в кастрюлю и варить"),
        CookingStepEntity(id = "", recipeId = "", description = "Кушать много, кушать вкусно")
    )
)

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
