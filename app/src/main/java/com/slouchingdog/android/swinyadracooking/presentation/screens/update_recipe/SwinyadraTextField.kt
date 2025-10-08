package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SwinyadraTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier,
        readOnly = readOnly,
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
            disabledBorderColor = MaterialTheme.colorScheme.tertiary,
            errorBorderColor = MaterialTheme.colorScheme.tertiary
        ),
        keyboardOptions = keyboardOptions,
        singleLine = singleLine
    )
}
