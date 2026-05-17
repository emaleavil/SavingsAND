package com.eeema.android.login

import androidx.compose.runtime.Stable

@Stable
internal data class LoginUiState(
    val isLoading: Boolean = false,
    val effect: LoginUiEffect? = null,
)
