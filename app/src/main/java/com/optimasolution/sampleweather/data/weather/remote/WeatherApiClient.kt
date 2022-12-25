package com.optimasolution.sampleweather.data.weather.remote

import com.optimasolution.sampleweather.data.weather.model.HourlyForecastResponse
import com.optimasolution.sampleweather.data.weather.model.SevenDayForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("hourly-forecast")
    suspend fun getHourlyForeCast(
        @Header("apikey") apiKey: String,
        @Header("Authorization") token: String,
        @Query("cityName") cityName: String
    ): Response<List<HourlyForecastResponse>>

    @GET("seven-days-forecast")
    suspend fun getSevenDayForecast(
        @Header("apikey") apiKey: String,
        @Header("Authorization") token: String,
        @Query("cityName") cityName: String
    ): Response<List<SevenDayForecastResponse>>
}