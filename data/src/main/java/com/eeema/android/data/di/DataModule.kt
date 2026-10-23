package com.eeema.android.data.di

import com.eeema.android.data.authentication.FirebaseAuthRepository
import com.eeema.android.domain.repository.AuthenticationRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::FirebaseAuthRepository) bind AuthenticationRepository::class
}
