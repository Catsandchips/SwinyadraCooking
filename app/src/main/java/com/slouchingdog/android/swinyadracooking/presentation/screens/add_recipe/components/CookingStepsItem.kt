package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.R

@Composable
fun CookingStepsItem(
    stepDescription: String,
    stepIndex: Int,
    stepsCount: Int,
    onStepChange: (String) -> Unit,
    onDeleteStep: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("${stringResource(R.string.step)} $stepIndex")
        TextField(
            value = stepDescription,
            onValueChange = { onStepChange(it) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            modifier = Modifier.background(Color.Transparent)
        )

        if (stepsCount > 1) {
            IconButton(onClick = { onDeleteStep() }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Delete step"
                )
            }
        }
    }
}