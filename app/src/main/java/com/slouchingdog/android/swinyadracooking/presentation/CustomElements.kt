package com.slouchingdog.android.swinyadracooking.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NumberCircle(
    number: Int,
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.secondary
    )
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(32.dp)
            .background(
                brush = Brush.verticalGradient(colors = gradientColors),
                shape = CircleShape
            )
    ) {
        Text(
            text = number.toString(),
            color = MaterialTheme.colorScheme.background,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.secondary
    ),
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                ), shape = RoundedCornerShape(12.dp)
            ),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
    ) { content() }
}

@Composable
fun PinkTrailingIcon(isExpanded: Boolean) {
    Icon(
        Icons.Default.KeyboardArrowDown,
        contentDescription = null,
        modifier = Modifier
            .rotate(if (isExpanded) 180f else 0f)
            .padding(0.dp),
        tint = MaterialTheme.colorScheme.tertiary
    )
}

@Composable
fun DotSeparator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .height(1.dp),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val dotSize = 2.dp.toPx()
            val dotSpacing = 3.dp.toPx()
            val width = size.width

            var currentX = 0f
            while (currentX < width) {
                drawCircle(
                    color = Color.Gray,
                    radius = dotSize / 2,
                    center = Offset(currentX, size.height / 2)
                )
                currentX += dotSize + dotSpacing
            }
        }
    }
}

@Composable
fun SwinyadraTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: (@Composable () -> Unit)? = null,
    suffix: (@Composable () -> Unit)? = null,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    isError: Boolean = false,
    supportingText: String = ""
) {
    OutlinedTextField(
        modifier = modifier,
        readOnly = readOnly,
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = placeholder,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
            disabledBorderColor = MaterialTheme.colorScheme.tertiary
        ),
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        suffix = suffix,
        isError = isError,
        supportingText = { if (isError) Text(supportingText) }
    )
}