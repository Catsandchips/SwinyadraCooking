package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.SwinyadraTextField

@Preview
@Composable
fun CPFCFields() {
    Column {
        Text(
            text = stringResource(R.string.nutritional_value_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
        Row {
            SwinyadraTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(stringResource(R.string.calories_field_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.25f)
            )
            SwinyadraTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(stringResource(R.string.proteins_field_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.25f)
            )
            SwinyadraTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(stringResource(R.string.fats_field_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.25f)
            )
            SwinyadraTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("У") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.25f)
            )
        }
    }
}