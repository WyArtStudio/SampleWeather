package com.optimasolution.sampleweather.data.weather.remote

import com.optimasolution.sampleweather.data.weather.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WeatherApiClient {
    @GET("sample-weather?select=*")
    suspend fun getAllWeather(
        @Header("apikey") apiKey: String,
        @Header("Authorization") token: String
    ): Response<List<WeatherResponse>>
}