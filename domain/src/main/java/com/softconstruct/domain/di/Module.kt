package com.softconstruct.domain.di

import com.softconstruct.domain.interactor.AllArticlesFragmentInteractor
import com.softconstruct.domain.interactor.FavoriteArticlesInteractor
import com.softconstruct.domain.usecase.AllArticlesFragmentUseCase
import com.softconstruct.domain.usecase.FavoriteArticlesUseCase
import org.koin.dsl.module

val interactorModule = module {
    single<AllArticlesFragmentInteractor> { AllArticlesFragmentUseCase(get()) }
    single<FavoriteArticlesInteractor> { FavoriteArticlesUseCase(get(), get()) }
}
