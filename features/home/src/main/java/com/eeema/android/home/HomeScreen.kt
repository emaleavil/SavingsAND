package com.eeema.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.eeema.android.add.api.Add
import com.eeema.android.components.navigation.navigate
import com.eeema.android.settings.api.Settings
import com.eeema.android.theme.SavingsTheme
import org.koin.compose.koinInject

enum class Tab {
    SUMMARY,
    TRANSACTIONS,
}

@Composable
fun HomeScreen(
    backStack: NavBackStack<NavKey>,
) {
    val viewModel = koinInject<HomeViewModel>()
    HomeContent(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onEvent = viewModel::onEvent,
        backStack = backStack
    )
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    backStack: NavBackStack<NavKey>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { HomeTopBar(onTopBarClicked = { backStack.navigate(Settings) }) },
        bottomBar = { HomeBottomBar(state.currentTab) { onEvent(HomeUiEvent.OnTabChanged(tab = it)) } },
        floatingActionButton = {
            FloatingActionButton(onClick = { backStack.navigate(to = Add) }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Transaction",
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            if (state.currentTab == Tab.SUMMARY) {
                SummaryView(
                    state = state.summary,
                    modifier = Modifier.matchParentSize()
                )
            } else {
                Text("Welcome to the List Screen!")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(
    onTopBarClicked: () -> Unit
) {
    TopAppBar(
        title = { Text("Current Month here") },
        actions = {
            IconButton(onClick = onTopBarClicked) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings",
                )
            }
        },
    )
}

@Composable
private fun HomeBottomBar(
    currentTab: Tab,
    onClicked: (Tab) -> Unit
) {
    BottomAppBar {
        IconButton(onClick = { onClicked(Tab.SUMMARY) }) {
            Icon(
                Icons.Filled.Home,
                contentDescription = "Summary",
                tint = if (currentTab == Tab.SUMMARY) colorScheme.primary else colorScheme.onSurface,
            )
        }
        IconButton(onClick = { onClicked(Tab.TRANSACTIONS) }) {
            Icon(
                Icons.AutoMirrored.Filled.List,
                contentDescription = "List of transactions",
                tint = if (currentTab == Tab.TRANSACTIONS) colorScheme.primary else colorScheme.onSurface,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        HomeContent(
            state = HomeUiState(currentTab = Tab.SUMMARY),
            onEvent = {},
            backStack = rememberNavBackStack()
        )
    }
}
