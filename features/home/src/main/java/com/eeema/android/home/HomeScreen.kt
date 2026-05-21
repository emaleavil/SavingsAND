package com.eeema.android.home

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.eeema.android.theme.SavingsTheme

@Composable
fun HomeScreen(
    backStack: NavBackStack<NavKey>,
) {
    Scaffold { innerPadding ->
        Text("Hello from home")
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        HomeScreen(backStack = rememberNavBackStack())
    }
}
