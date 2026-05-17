package com.eeema.android.login

internal sealed interface LoginUiEffect {
    data object NavigateToHome : LoginUiEffect
    data class ShowError(val message: String) : LoginUiEffect
}
