package com.softconstruct.task.di

import com.softconstruct.task.fragment.homefragment.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeFragmentViewModel(get()) }
}
