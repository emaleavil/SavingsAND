package com.eeema.android.savings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.eeema.android.login.TempNavigationLogin
import com.eeema.android.theme.SavingsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SavingsTheme {
                TempNavigationLogin()
            }
        }
    }
}
