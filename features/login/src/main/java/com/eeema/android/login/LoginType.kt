package com.eeema.android.login

internal sealed interface LoginType {
    data object SignUp : LoginType
    data object SignIn : LoginType
}
