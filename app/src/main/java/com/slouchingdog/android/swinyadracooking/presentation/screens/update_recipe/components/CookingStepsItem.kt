package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.NumberCircle
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.SwinyadraTextField

@Composable
fun CookingStepsItem(
    stepDescription: String,
    stepIndex: Int,
    stepsCount: Int,
    onStepChange: (String) -> Unit,
    onDeleteStep: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        NumberCircle(stepIndex)
        SwinyadraTextField(
            value = stepDescription,
            onValueChange = { onStepChange(it) }
        )

        if (stepsCount > 1) {
            IconButton(onClick = { onDeleteStep() }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Delete step",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}