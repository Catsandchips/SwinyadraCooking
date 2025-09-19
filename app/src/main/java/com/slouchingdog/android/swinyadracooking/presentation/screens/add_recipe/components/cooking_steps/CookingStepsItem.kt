package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.cooking_steps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.R

@Composable
@Preview
fun CookingStepsItem(stepCount: Int = 1) {
    Card(modifier = Modifier.background(Color.Transparent)) {
        Row(
            modifier = Modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("${stringResource(R.string.step)} $stepCount")
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(value = "", onValueChange = {})
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Удалить"
                    )
                }
            }
        }
    }
}