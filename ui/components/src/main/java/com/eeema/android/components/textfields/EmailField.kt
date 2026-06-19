package com.eeema.android.components.textfields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.eeema.android.theme.SavingsTheme

@Composable
fun EmailField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    label: String,
    onKeyboardActions: KeyboardActionHandler? = null,
) {
    OutlinedTextField(
        state = state,
        label = { Text(label) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            showKeyboardOnFocus = true,
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        onKeyboardAction = onKeyboardActions,
    )
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        EmailField(
            state = rememberTextFieldState(),
            label = "Email",
        )
    }
}
