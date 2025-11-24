package com.slouchingdog.android.swinyadracooking.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PiggyPeach,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF976C6),
    secondary = Color(0xFFFF60AB),
    tertiary = Color(0xFFFFB6BF),
    surface = Color(0xFFFFD1DC),
    background = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFFFE6EA),
    onSecondary = Color(0xFFFFB6C1),
    onTertiary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    secondaryContainer = Color(0xFFC7C6C6)

)

@Composable
fun SwinyadraCookingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}