package com.eeema.android.domain.di

import com.eeema.android.domain.usecases.GetTransactionsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetTransactionsUseCase)
}
