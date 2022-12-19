package com.optimasolution.sampleweather.domain.weather

import com.optimasolution.sampleweather.domain.util.Resource
import com.optimasolution.sampleweather.domain.weather.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {
    suspend fun getAllWeather(apiKey: String, token: String): Flow<Resource<List<Weather>>>
}