package com.eeema.android.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import org.koin.androidx.compose.koinViewModel

enum class Tab {
    SUMMARY,
    TRANSACTIONS,
}

@Composable
fun HomeScreen(
    backStack: NavBackStack<NavKey>,
) {
    val viewModel = koinViewModel<HomeViewModel>()
    HomeContent(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onEvent = viewModel::onEvent,
        backStack = backStack,
    )
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    backStack: NavBackStack<NavKey>,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { HomeTopBar(onTopBarClicked = { backStack.navigate(Settings) }) },
        bottomBar = { HomeBottomBar(state.currentTab) { onEvent(HomeUiEvent.OnTabChanged(tab = it)) } },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (state.isLoading) return@FloatingActionButton
                    backStack.navigate(to = Add)
                },
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                } else {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Transaction",
                    )
                }
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
                .padding(horizontal = 20.dp),
        ) {
            if (state.currentTab == Tab.SUMMARY) {
                SummaryView(
                    state = state.summary,
                    modifier = Modifier.matchParentSize(),
                )
            } else {
                AnimatedVisibility(
                    visible = !state.isLoading,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    TransactionsView(
                        state = state.transactions,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(
    onTopBarClicked: () -> Unit,
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
    onClicked: (Tab) -> Unit,
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
            state = HomeUiState(
                isLoading = false,
                currentTab = Tab.TRANSACTIONS,
                summary = SummaryUiState(
                    percentage = 30f,
                    spent = 700f,
                    remaining = 1000f,
                ),
                transactions = TransactionsUiState(
                    listOf(
                        TransactionUiModel(
                            id = "123L",
                            description = "Salario",
                            quantity = "2500$",
                            date = "7/10/2026",
                            isIncome = true,
                        ),
                        TransactionUiModel(
                            id = "124L",
                            description = "Compra Mercadona",
                            quantity = "123$",
                            date = "18/10/2026",
                            isIncome = false,
                        ),
                        TransactionUiModel(
                            id = "125L",
                            description = "Gasolina",
                            quantity = "53$",
                            date = "13/10/2026",
                            isIncome = false,
                        ),
                    ),
                ),
            ),
            onEvent = {},
            backStack = rememberNavBackStack(),
        )
    }
}
