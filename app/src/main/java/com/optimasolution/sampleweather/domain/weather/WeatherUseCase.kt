package com.optimasolution.sampleweather.domain.weather

import com.optimasolution.sampleweather.data.weather.model.HourlyForecastResponse
import com.optimasolution.sampleweather.data.weather.model.SevenDayForecastResponse
import com.optimasolution.sampleweather.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {
    suspend fun getHourlyForecast(apiKey: String, token: String, cityName: String): Flow<Resource<List<HourlyForecastResponse>>>
    suspend fun getSevenDayForecast(apiKey: String, token: String, cityName: String): Flow<Resource<List<SevenDayForecastResponse>>>
}