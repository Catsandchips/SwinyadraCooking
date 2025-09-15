package com.slouchingdog.android.swinyadracooking.ui.screens.add_recipe.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import com.slouchingdog.android.swinyadracooking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DishTypeDropdownMenu(
    dishType: Int,
    isExpanded: Boolean,
    onExpandedChange: () -> Unit,
    onTypeSelection: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    val options = stringArrayResource(R.array.dish_types_array)
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { onExpandedChange() },
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .fillMaxWidth(),
            readOnly = true,
            value = options[dishType],
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
        )
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { onDismissRequest() }) {
            options.forEach { selectedType ->
                DropdownMenuItem(
                    text = { Text(selectedType) },
                    onClick = {
                        onTypeSelection(options.indexOf(selectedType))
                        onExpandedChange()
                    }
                )
            }
        }
    }
}