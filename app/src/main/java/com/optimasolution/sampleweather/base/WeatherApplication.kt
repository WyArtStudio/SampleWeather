package com.optimasolution.sampleweather.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.optimasolution.sampleweather.BuildConfig
import com.optimasolution.sampleweather.di.networkModule
import com.optimasolution.sampleweather.di.repositoryModule
import com.optimasolution.sampleweather.di.useCaseModule
import com.optimasolution.sampleweather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.NONE

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(NONE)
            androidContext(this@WeatherApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}