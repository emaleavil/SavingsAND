package com.eeema.android.add.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.metadata
import androidx.navigation3.ui.NavDisplay
import com.eeema.android.add.AddScreen
import com.eeema.android.add.api.Add
import kotlinx.serialization.modules.PolymorphicModuleBuilder

fun EntryProviderScope<NavKey>.addEntries(backStack: NavBackStack<NavKey>) {
    entry<Add>(
        metadata = metadata {
            put(NavDisplay.TransitionKey) {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(1000),
                ) togetherWith ExitTransition.KeepUntilTransitionsFinished
            }
            put(NavDisplay.PopTransitionKey) {
                EnterTransition.None togetherWith
                    slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(1000),
                    )
            }
            put(NavDisplay.PredictivePopTransitionKey) {
                EnterTransition.None togetherWith
                    slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(1000),
                    )
            }
        },
    ) { AddScreen(backStack = backStack) }
}

fun PolymorphicModuleBuilder<NavKey>.addAddSubclasses() = with(this) {
    subclass(Add::class, Add.serializer())
}
