package com.eeema.android.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.eeema.android.theme.SavingsTheme

@Composable
fun LoginScreen() {
    Text("Hello from login screen!")
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        LoginScreen()
    }
}
