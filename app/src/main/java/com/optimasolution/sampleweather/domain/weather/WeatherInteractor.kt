package com.optimasolution.sampleweather.domain.weather

import com.optimasolution.sampleweather.data.weather.WeatherRepository
import com.optimasolution.sampleweather.data.weather.model.HourlyForecastResponse
import com.optimasolution.sampleweather.data.weather.model.SevenDayForecastResponse
import com.optimasolution.sampleweather.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class WeatherInteractor(private val repository: WeatherRepository): WeatherUseCase {

    override suspend fun getHourlyForecast(apiKey: String, token: String, cityName: String): Flow<Resource<List<HourlyForecastResponse>>> {
        return repository.getHourlyForecast(apiKey, token, cityName)
    }

    override suspend fun getSevenDayForecast(
        apiKey: String,
        token: String,
        cityName: String
    ): Flow<Resource<List<SevenDayForecastResponse>>> {
        return repository.getSevenDayForecast(apiKey, token, cityName)
    }
}