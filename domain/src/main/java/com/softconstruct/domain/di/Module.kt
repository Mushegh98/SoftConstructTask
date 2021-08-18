package com.softconstruct.domain.di

import com.softconstruct.domain.interactor.HomeFragmentInteractor
import com.softconstruct.domain.usecase.HomeFragmentUseCase
import org.koin.dsl.module

val interactorModule = module {
    single<HomeFragmentInteractor> { HomeFragmentUseCase(get()) }
}
