package com.eeema.android.settings

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.eeema.android.theme.SavingsTheme

@Composable
fun SettingsScreen(
    backStack: NavBackStack<NavKey>,
) {
    Scaffold { innerPadding ->
        Text("Settings Screen")
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        SettingsScreen(
            backStack = rememberNavBackStack(),
        )
    }
}
