package com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity

@Composable
fun RecipeCard(recipeDetailedEntity: RecipeDetailedEntity) {
    Card() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.piggy_cooking_logo_small_hat_20250926102728_1),
                contentDescription = "Dish photo",
                Modifier.fillMaxWidth()
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(recipeDetailedEntity.recipeEntity.name, fontWeight = FontWeight.Bold)
                Text(stringArrayResource(R.array.dish_types_array)[recipeDetailedEntity.recipeEntity.dishType])
                Text("${recipeDetailedEntity.recipeEntity.cookingTime} ${stringResource(R.string.minutes_string)}")
            }

        }
    }
}