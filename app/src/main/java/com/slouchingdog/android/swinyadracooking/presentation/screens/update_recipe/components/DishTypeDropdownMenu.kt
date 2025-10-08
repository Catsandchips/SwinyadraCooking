package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.PinkTrailingIcon
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.SwinyadraTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DishTypeDropdownMenu(
    dishType: Int,
    isExpanded: Boolean,
    onExpandedChange: () -> Unit,
    onTypeSelection: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val options = stringArrayResource(R.array.dish_types_array)
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { onExpandedChange() }) {
        SwinyadraTextField(
            modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryEditable),
            readOnly = true,
            value = options[dishType],
            onValueChange = {},
            suffix = { PinkTrailingIcon(isExpanded) }
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