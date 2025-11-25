package com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BreakfastDining
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.DinnerDining
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material.icons.outlined.LunchDining
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity

@Composable
fun RecipeCard(recipeDetailedEntity: RecipeDetailedEntity, onCardClick: (String) -> Unit) {
    Card(
        onClick = { onCardClick(recipeDetailedEntity.recipeEntity.id!!) },
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .size(150.dp)
            ) {
                if (recipeDetailedEntity.recipeEntity.imageUri != null) {
                    AsyncImage(
                        model = recipeDetailedEntity.recipeEntity.imageUri,
                        contentDescription = stringResource(R.string.dish_photo),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = getDishIcon(recipeDetailedEntity.recipeEntity.dishType),
                        contentDescription = stringResource(R.string.dish_photo),
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = recipeDetailedEntity.recipeEntity.name,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Column {
                        Row {
                            Icon(
                                imageVector = getDishIcon(recipeDetailedEntity.recipeEntity.dishType),
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = stringArrayResource(R.array.dish_types_array)[recipeDetailedEntity.recipeEntity.dishType],
                            )
                        }
                        Row {
                            Icon(
                                imageVector = Icons.Outlined.Timer,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                "${recipeDetailedEntity.recipeEntity.cookingTime} ${
                                    stringResource(
                                        R.string.minutes_string
                                    )
                                }"
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getDishIcon(dishType: Int): ImageVector {
    return when (dishType) {
        0 -> Icons.Outlined.BreakfastDining
        1 -> Icons.Outlined.LunchDining
        2 -> Icons.Outlined.DinnerDining
        3 -> Icons.Outlined.Cookie
        4 -> Icons.Outlined.LocalBar
        else -> Icons.Outlined.LunchDining
    }
}