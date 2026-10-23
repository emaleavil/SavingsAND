package com.eeema.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.placeholder: Color
    @Composable
    get() = if (isSystemInDarkTheme()) PlaceholderColor else PlaceholderColorLight
