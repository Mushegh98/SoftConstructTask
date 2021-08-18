package com.softconstruct.task

import android.app.Application
import com.softconstruct.data.di.apiModule
import com.softconstruct.data.di.databaseModule
import com.softconstruct.data.di.repositoryModule
import com.softconstruct.data.di.serviceModule
import com.softconstruct.domain.di.interactorModule
import com.softconstruct.task.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }

    private val modules = listOf(
        viewModelModule,
        databaseModule,
        apiModule,
        repositoryModule,
        serviceModule,
        interactorModule,
    )
}