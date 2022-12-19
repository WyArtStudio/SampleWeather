package com.optimasolution.sampleweather.data.weather.remote

import com.optimasolution.sampleweather.data.weather.model.WeatherResponse
import retrofit2.Response

class WeatherApi(private val api: WeatherApiClient): WeatherApiClient {

    override suspend fun getAllWeather(apiKey: String, token: String): Response<List<WeatherResponse>> {
        return api.getAllWeather(apiKey, token)
    }
}