package com.eeema.android.savings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.eeema.android.components.navigation.NavigationRoot
import com.eeema.android.login.api.SignIn
import com.eeema.android.login.navigation.addLoginSubclasses
import com.eeema.android.login.navigation.loginEntries
import com.eeema.android.theme.SavingsTheme
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SavingsTheme {
                NavigationRoot(
                    startRoute = SignIn,
                    config = SavedStateConfiguration {
                        serializersModule = SerializersModule {
                            polymorphic(baseClass = NavKey::class) {
                                addLoginSubclasses()
                            }
                        }
                    },
                    addEntries = { scope, backStack ->
                        scope.loginEntries(backStack)
                    },
                )
            }
        }
    }
}
