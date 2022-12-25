package com.optimasolution.sampleweather.data.weather

import com.optimasolution.sampleweather.data.util.call
import com.optimasolution.sampleweather.data.util.mapToDomain
import com.optimasolution.sampleweather.data.weather.model.HourlyForecastResponse
import com.optimasolution.sampleweather.data.weather.model.SevenDayForecastResponse
import com.optimasolution.sampleweather.data.weather.remote.WeatherApiClient
import com.optimasolution.sampleweather.domain.util.Resource
import com.optimasolution.sampleweather.util.addBearerToken
import com.optimasolution.sampleweather.util.toQuery
import kotlinx.coroutines.flow.Flow

class WeatherDataStore(private val webService: WeatherApiClient): WeatherRepository {

    override suspend fun getHourlyForecast(apiKey: String, token: String, cityName: String): Flow<Resource<List<HourlyForecastResponse>>> {
        return webService.getHourlyForeCast(apiKey, token.addBearerToken(), cityName.toQuery()).call().mapToDomain { it }
    }

    override suspend fun getSevenDayForecast(
        apiKey: String,
        token: String,
        cityName: String
    ): Flow<Resource<List<SevenDayForecastResponse>>> {
        return webService.getSevenDayForecast(apiKey, token.addBearerToken(), cityName.toQuery()).call().mapToDomain { it }
    }
}