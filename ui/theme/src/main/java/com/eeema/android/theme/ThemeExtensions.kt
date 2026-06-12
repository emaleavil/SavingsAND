package com.eeema.android.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val MaterialTheme.outlinedTextFieldColors: TextFieldColors
    @Composable
    get() =
        OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorScheme.onPrimary,
            unfocusedTextColor = colorScheme.onPrimary,
            errorTextColor = colorScheme.onPrimary,
            errorPlaceholderColor = colorScheme.placeholder,
            focusedBorderColor = colorScheme.secondary,
            unfocusedBorderColor = colorScheme.tertiary,
            unfocusedPlaceholderColor = colorScheme.placeholder,
            focusedPlaceholderColor = colorScheme.placeholder,
        )

val ColorScheme.backgroundColor600: Color
    get() = BackgroundColor600
