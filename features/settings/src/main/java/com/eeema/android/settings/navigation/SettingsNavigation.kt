package com.eeema.android.settings.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.metadata
import androidx.navigation3.ui.NavDisplay
import com.eeema.android.settings.SettingsScreen
import com.eeema.android.settings.api.Settings
import kotlinx.serialization.modules.PolymorphicModuleBuilder

fun EntryProviderScope<NavKey>.settingsEntries(backStack: NavBackStack<NavKey>) {
    entry<Settings>(
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(1000),
                ) togetherWith ExitTransition.KeepUntilTransitionsFinished
            }
            put(NavDisplay.PopTransitionKey) {
                EnterTransition.None togetherWith
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(1000),
                    )
            }
            put(NavDisplay.PredictivePopTransitionKey) {
                EnterTransition.None togetherWith
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(1000),
                    )
            }
        },
    ) { SettingsScreen(backStack = backStack) }
}

fun PolymorphicModuleBuilder<NavKey>.addSettingsSubclasses() = with(this) {
    subclass(Settings::class, Settings.serializer())
}
