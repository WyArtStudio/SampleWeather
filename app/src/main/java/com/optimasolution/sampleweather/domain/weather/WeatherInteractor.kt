package com.optimasolution.sampleweather.domain.weather

import com.optimasolution.sampleweather.data.weather.WeatherRepository
import com.optimasolution.sampleweather.domain.util.Resource
import com.optimasolution.sampleweather.domain.weather.model.Weather
import kotlinx.coroutines.flow.Flow

class WeatherInteractor(private val repository: WeatherRepository): WeatherUseCase {

    override suspend fun getAllWeather(apiKey: String, token: String): Flow<Resource<List<Weather>>> {
        return repository.getAllWeather(apiKey, token)
    }
}