package com.optimasolution.sampleweather.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor.Builder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.optimasolution.sampleweather.data.weather.WeatherDataStore
import com.optimasolution.sampleweather.data.weather.WeatherRepository
import com.optimasolution.sampleweather.data.weather.remote.WeatherApi
import com.optimasolution.sampleweather.data.weather.remote.WeatherApiClient
import com.optimasolution.sampleweather.domain.weather.WeatherInteractor
import com.optimasolution.sampleweather.domain.weather.WeatherUseCase
import com.optimasolution.sampleweather.util.BASE_URL
import com.optimasolution.sampleweather.viewmodel.WeatherViewModel
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { CompositeDisposable() }

    val httpLogging = "http_logging"
    val chuckerLogging = "chucker_logging"

    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    single<Interceptor>(named(httpLogging)) {
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) BODY else NONE
        )
    }

    single<Interceptor>(named(chuckerLogging)) {
        Builder(get())
            .build()
    }

    fun cache(context: Context): Cache {
        val cacheSize = (5.times(1024).times(1024)).toLong()
        return Cache(context.cacheDir, cacheSize)
    }

    single {
        OkHttpClient.Builder()
            .cache(cache(get()))
            .addInterceptor { chain ->
                // Get the request from the chain.
                val request = chain.request()
                request.newBuilder().header("Cache-Control", "public, max-age=" + 10).build()
                chain.proceed(request)
            }
            .build()
    }

    single<WeatherApiClient> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
            .create(WeatherApiClient::class.java)
    }

    single {
        WeatherApi(get())
    }
}

val repositoryModule = module {
    single<WeatherRepository> { WeatherDataStore(get()) }
}

val useCaseModule = module {
    single<WeatherUseCase> { WeatherInteractor(get()) }
}

val viewModelModule = module {
    viewModel { WeatherViewModel(get(), get()) }
}