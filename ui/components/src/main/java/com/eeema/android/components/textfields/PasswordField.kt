package com.eeema.android.components.textfields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.eeema.android.theme.SavingsTheme

@Composable
fun PasswordField(
    state: TextFieldState,
    label: String,
    modifier: Modifier = Modifier,
    isHidden: Boolean = true,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    var isHidden by remember { mutableStateOf(isHidden) }

    if (isHidden) {
        OutlinedSecureTextField(
            state = state,
            modifier = modifier,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                showKeyboardOnFocus = true,
            ),
            onKeyboardAction = onKeyboardAction,
            trailingIcon = {
                IconButton(onClick = { isHidden = !isHidden }) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null,
                    )
                }
            },
        )
    } else {
        OutlinedTextField(
            state = state,
            label = { Text(label) },
            modifier = modifier,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                showKeyboardOnFocus = true,
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            onKeyboardAction = onKeyboardAction,
            trailingIcon = {
                IconButton(onClick = { isHidden = !isHidden }) {
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = null,
                    )
                }
            },
        )
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        PasswordField(
            state = rememberTextFieldState(),
            label = "Password",
        )
    }
}
