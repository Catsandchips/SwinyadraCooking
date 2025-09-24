package com.slouchingdog.android.swinyadracooking.presentation.screens.add_recipe.components.cooking_steps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.slouchingdog.android.swinyadracooking.R


@Composable
@Preview
fun CookingStepsList() {
    val viewModel: CookingStepsViewModel = viewModel()
    val cookingSteps by viewModel.cookingSteps.collectAsState()
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(stringResource(R.string.cooking_steps_title))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(cookingSteps) { cookingStep ->
                CookingStepsItem(
                    stepDescription = cookingStep.stepDescription,
                    stepIndex = cookingSteps.indexOf(cookingStep) + 1,
                    stepsCount = cookingSteps.size,
                    onStepChange = {
                        viewModel.onStepUpdate(
                            cookingSteps.indexOf(cookingStep),
                            it
                        )
                    },
                    onDeleteStep = { viewModel.onStepRemove(cookingSteps.indexOf(cookingStep)) })
            }
        }
        TextButton(
            onClick = { viewModel.onStepAdd() },
            modifier = Modifier.fillMaxWidth()) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add cooking step",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(stringResource(R.string.add_step_button_text))
        }
    }
}