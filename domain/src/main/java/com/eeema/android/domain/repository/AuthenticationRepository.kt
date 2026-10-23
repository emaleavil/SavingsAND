package com.eeema.android.domain.repository

interface AuthenticationRepository {

    suspend fun access(email: String, password: String)

    suspend fun register(email: String, password: String)

    suspend fun logout()
}
