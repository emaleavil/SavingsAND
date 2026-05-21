package com.eeema.android.components.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration

@Composable
fun NavigationRoot(
    startRoute: NavKey,
    config: SavedStateConfiguration,
    addEntries: (EntryProviderScope<NavKey>, NavBackStack<NavKey>) -> Unit,
) {
    val backStack = rememberNavBackStack(
        configuration = config,
        elements = arrayOf(startRoute),
    )

    NavDisplay(
        backStack = backStack,
        modifier = Modifier.fillMaxSize(),
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            addEntries(this, backStack)
        },
    )
}

fun NavBackStack<NavKey>.goBack() {
    removeLastOrNull()
}

fun NavBackStack<NavKey>.navigate(to: NavKey, clearPrevious: Boolean) {
    if (clearPrevious) removeLastOrNull()
    add(to)
}
