package com.eeema.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
    darkColorScheme(
        primary = MainColor,
        secondary = SecondaryColor,
        tertiary = Tertiary,
        background = Background,
        surface = Background,
        error = ErrorColor,
        onBackground = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.White,
        onTertiary = Color.White,
        onSurface = Color.White,
        onError = Color.White,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = MainColorLight,
        secondary = SecondaryColorLight,
        tertiary = TertiaryLight,
        background = BackgroundLight,
        surface = BackgroundLight,
        error = ErrorColorLight,
        onBackground = Color.Black,
        onPrimary = Color.White,
        onSecondary = Color.White,
        onTertiary = Color.White,
        onSurface = Color(0xFF4C4C4C),
        onError = Color.Black,
    )

@Composable
fun SavingsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content,
        shapes = shapes,
    )
}
