package com.eeema.android.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class LoginViewModel : ViewModel() {

    private var _state: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnSubmit -> {
                _state.update { it.copy(isLoading = true) }
                when (event.type) {
                    LoginType.SignUp -> register(event.email, event.password)
                    LoginType.SignIn -> access(event.email, event.password)
                }
            }

            is LoginUiEvent.OnConsumeEffect -> _state.update { it.copy(effect = null) }
        }
    }

    private fun register(email: String, password: String) {
        Log.i("LoginViewModel", "register: $email, $password")
        viewModelScope.launch {
            delay(2000)
            _state.update {
                it.copy(
                    isLoading = false,
                    effect = if (email == "register@email.com") {
                        LoginUiEffect.NavigateToHome
                    } else {
                        LoginUiEffect.ShowError("Invalid credentials")
                    },
                )
            }
        }
    }

    private fun access(email: String, password: String) {
        Log.i("LoginViewModel", "access: $email, $password")
        viewModelScope.launch {
            delay(2000)
            _state.update {
                it.copy(
                    isLoading = false,
                    effect = if (email == "access@email.com") {
                        LoginUiEffect.NavigateToHome
                    } else {
                        LoginUiEffect.ShowError("Invalid credentials")
                    },
                )
            }
        }
    }
}
