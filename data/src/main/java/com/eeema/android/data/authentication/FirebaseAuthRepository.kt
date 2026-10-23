package com.eeema.android.data.authentication

import com.eeema.android.domain.repository.AuthenticationRepository

internal class FirebaseAuthRepository : AuthenticationRepository {
    override suspend fun access(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun register(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}
