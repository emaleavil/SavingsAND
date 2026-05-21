package com.eeema.android.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.eeema.android.components.effect.LaunchEffectHandler
import com.eeema.android.components.navigation.ClearBackStack
import com.eeema.android.components.navigation.goBack
import com.eeema.android.components.navigation.navigate
import com.eeema.android.home.api.Home
import com.eeema.android.login.api.SignUp
import com.eeema.android.theme.SavingsTheme
import kotlinx.coroutines.launch

@Composable
internal fun LoginScreen(
    type: LoginType,
    backStack: NavBackStack<NavKey>,
) {
    val viewModel = viewModel<LoginViewModel>()
    LoginContent(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        type = type,
        onEvent = viewModel::onEvent,
        backStack = backStack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginContent(
    state: LoginUiState,
    type: LoginType,
    onEvent: (LoginUiEvent) -> Unit,
    backStack: NavBackStack<NavKey>,
) {
    val email = rememberTextFieldState()
    val password = rememberTextFieldState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    if (type is LoginType.SignUp) {
                        IconButton(onClick = { backStack.goBack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Close",
                            )
                        }
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    state = email,
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    state = password,
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    "Don't have an account? Sign Up",
                    modifier = Modifier
                        .align(Alignment.End)
                        .alpha(if (type is LoginType.SignIn) 1f else 0f)
                        .clickable(role = Role.Button) { backStack.navigate(to = SignUp, clear = ClearBackStack.NONE) },
                )

                Button(
                    onClick = {
                        onEvent(
                            LoginUiEvent.OnSubmit(
                                email = email.text.toString(),
                                password = password.text.toString(),
                                type = type,
                            ),
                        )
                    },
                ) {
                    Box(
                        modifier = Modifier.width(120.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                color = Color.White,
                                strokeCap = StrokeCap.Square,
                                modifier = Modifier.size(24.dp),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        } else {
                            Text(
                                text = if (type is LoginType.SignIn) "Sign In" else "Sign Up",
                                maxLines = 1,
                            )
                        }
                    }
                }
            }
        }
    }
    LaunchEffectHandler(
        state.effect,
        onEffect = {
            when (it) {
                is LoginUiEffect.NavigateToHome -> {
                    backStack.navigate(
                        to = Home,
                        clear = if (type == LoginType.SignIn) ClearBackStack.PREVIOUS else ClearBackStack.ALL,
                    )
                }

                is LoginUiEffect.ShowError -> scope.launch { snackbarHostState.showSnackbar(it.message) }

                else -> { /*No op*/ }
            }
        },
        onConsumeEffect = { onEvent(LoginUiEvent.OnConsumeEffect) },
    )
}

@PreviewLightDark
@Composable
private fun Preview(
    @PreviewParameter(PreviewProvider::class) data: LoginScreenPreviewData,
) {
    SavingsTheme {
        LoginContent(
            state = data.state,
            type = data.type,
            onEvent = {},
            backStack = rememberNavBackStack(),
        )
    }
}

internal data class LoginScreenPreviewData(
    val type: LoginType = LoginType.SignIn,
    val state: LoginUiState = LoginUiState(),
)

internal class PreviewProvider : PreviewParameterProvider<LoginScreenPreviewData> {
    override val values: Sequence<LoginScreenPreviewData>
        get() = sequenceOf(
            LoginScreenPreviewData(LoginType.SignIn, LoginUiState(isLoading = false)),
            LoginScreenPreviewData(LoginType.SignIn, LoginUiState(isLoading = true)),
            LoginScreenPreviewData(
                LoginType.SignIn,
                LoginUiState(isLoading = false, effect = LoginUiEffect.ShowError("Error message")),
            ),
            LoginScreenPreviewData(LoginType.SignUp, LoginUiState(isLoading = false)),
            LoginScreenPreviewData(LoginType.SignUp, LoginUiState(isLoading = true)),
            LoginScreenPreviewData(
                LoginType.SignUp,
                LoginUiState(isLoading = false, effect = LoginUiEffect.ShowError("Error message")),
            ),
        )
}
