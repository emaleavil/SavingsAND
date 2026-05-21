package com.eeema.android.home.navigation

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
import com.eeema.android.home.HomeScreen
import com.eeema.android.home.api.Home
import kotlinx.serialization.modules.PolymorphicModuleBuilder

fun EntryProviderScope<NavKey>.homeEntries(backStack: NavBackStack<NavKey>) {
    entry<Home>(
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
    ) { HomeScreen(backStack = backStack) }
}

fun PolymorphicModuleBuilder<NavKey>.addHomeSubclasses() = with(this) {
    subclass(Home::class, Home.serializer())
}
