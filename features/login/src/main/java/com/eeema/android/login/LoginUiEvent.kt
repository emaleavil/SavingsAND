package com.eeema.android.login

internal sealed interface LoginUiEvent {
    data class OnSubmit(
        val email: String,
        val password: String,
        val type: LoginType,
    ) : LoginUiEvent

    data object OnConsumeEffect : LoginUiEvent
}
