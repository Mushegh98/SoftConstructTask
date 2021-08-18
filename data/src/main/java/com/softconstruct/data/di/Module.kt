package com.softconstruct.data.di

import android.app.Application
import androidx.room.Room
import com.softconstruct.data.dataservice.RetrofitService
import com.softconstruct.data.dataservice.appservice.PreferenceService
import com.softconstruct.data.dataservice.appservice.PreferenceServiceImpl
import com.softconstruct.data.dataservice.sqlservice.AppDatabase
import com.softconstruct.data.datastore.HomeFragmentRepository
import com.softconstruct.data.repository.HomeFragmentRepositoryImpl
import com.softconstruct.data.utils.MAIN_URL
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { Moshi.Builder().build() }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(120, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.HEADERS
                        })
                        .build()
                )
            }
            .build()
    }

    single<RetrofitService> { get<Retrofit>().create(RetrofitService::class.java) }

}
val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "GuardianTaskDB")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { provideDatabase(androidApplication()) }
    single { get<AppDatabase>().detailsDao() }
}

val repositoryModule = module {
    single<HomeFragmentRepository> { HomeFragmentRepositoryImpl(get()) }
}

val serviceModule = module {
    single<PreferenceService> { PreferenceServiceImpl(get()) }
}