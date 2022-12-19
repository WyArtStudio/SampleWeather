package com.optimasolution.sampleweather.data.weather

import com.optimasolution.sampleweather.domain.util.Resource
import com.optimasolution.sampleweather.domain.weather.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getAllWeather(apiKey: String, token: String): Flow<Resource<List<Weather>>>
}