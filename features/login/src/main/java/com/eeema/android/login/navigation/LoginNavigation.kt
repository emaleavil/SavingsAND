package com.eeema.android.login.navigation

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
import com.eeema.android.login.LoginScreen
import com.eeema.android.login.LoginType
import com.eeema.android.login.api.SignIn
import com.eeema.android.login.api.SignUp
import kotlinx.serialization.modules.PolymorphicModuleBuilder

fun EntryProviderScope<NavKey>.loginEntries(backStack: NavBackStack<NavKey>) {
    entry<SignIn> {
        LoginScreen(type = LoginType.SignIn, backStack)
    }
    entry<SignUp>(
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
    ) { LoginScreen(type = LoginType.SignUp, backStack = backStack) }
}

fun PolymorphicModuleBuilder<NavKey>.addLoginSubclasses() = with(this) {
    subclass(SignIn::class, SignIn.serializer())
    subclass(SignUp::class, SignUp.serializer())
}
