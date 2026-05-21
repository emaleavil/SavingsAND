package com.eeema.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.eeema.android.theme.SavingsTheme

enum class Tab {
    SUMMARY,
    TRANSACTIONS,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    backStack: NavBackStack<NavKey>,
) {
    var currentTab by remember { mutableStateOf(Tab.SUMMARY) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Current Month here") },
            )
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { currentTab = Tab.SUMMARY }) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Summary View",
                        tint = if (currentTab == Tab.SUMMARY) colorScheme.primary else colorScheme.onSurface,
                    )
                }
                IconButton(onClick = { currentTab = Tab.TRANSACTIONS }) {
                    Icon(
                        Icons.AutoMirrored.Filled.List,
                        contentDescription = "List of transactions",
                        tint = if (currentTab == Tab.TRANSACTIONS) colorScheme.primary else colorScheme.onSurface,
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*backStack.navigate(to = ADD)*/ }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Transaction",
                )
            }
        },
    ) {
        Box(modifier = Modifier.padding(it).padding(horizontal = 20.dp)) {
            if (currentTab == Tab.SUMMARY) {
                Text("Welcome to the Summary Screen!")
            } else {
                Text("Welcome to the List Screen!")
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        HomeScreen(backStack = rememberNavBackStack())
    }
}
