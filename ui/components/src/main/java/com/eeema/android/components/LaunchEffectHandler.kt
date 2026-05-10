package com.eeema.android.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun <EFFECT> LaunchEffectHandler(
    effect: EFFECT,
    onEffect: suspend (effect: EFFECT) -> Unit,
    onConsumeEffect: () -> Unit,
) {
    val currentOnEffect by rememberUpdatedState(onEffect)
    val currentOnConsumeEffect by rememberUpdatedState(onConsumeEffect)

    LaunchedEffect(effect) {
        effect?.let {
            currentOnEffect(it)
            currentOnConsumeEffect()
        }
    }
}
