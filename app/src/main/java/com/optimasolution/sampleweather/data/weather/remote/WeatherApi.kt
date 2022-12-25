package com.optimasolution.sampleweather.data.weather.remote

import com.optimasolution.sampleweather.data.weather.model.HourlyForecastResponse
import com.optimasolution.sampleweather.data.weather.model.SevenDayForecastResponse
import retrofit2.Response

class WeatherApi(private val api: WeatherApiClient): WeatherApiClient {

    override suspend fun getHourlyForeCast(apiKey: String, token: String, cityName: String): Response<List<HourlyForecastResponse>> {
        return api.getHourlyForeCast(apiKey, token, cityName)
    }

    override suspend fun getSevenDayForecast(apiKey: String, token: String, cityName: String): Response<List<SevenDayForecastResponse>> {
        return api.getSevenDayForecast(apiKey, token, cityName)
    }
}