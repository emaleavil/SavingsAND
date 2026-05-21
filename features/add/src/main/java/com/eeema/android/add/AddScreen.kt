package com.eeema.android.add

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.eeema.android.components.navigation.goBack
import com.eeema.android.theme.SavingsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    backStack: NavBackStack<NavKey>,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Entry") },
                navigationIcon = {
                    IconButton(onClick = { backStack.goBack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Text("Add Screen")
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        AddScreen(backStack = rememberNavBackStack())
    }
}
