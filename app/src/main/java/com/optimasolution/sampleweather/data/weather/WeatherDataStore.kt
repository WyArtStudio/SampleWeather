package com.optimasolution.sampleweather.data.weather

import com.optimasolution.sampleweather.data.util.call
import com.optimasolution.sampleweather.data.util.mapToDomain
import com.optimasolution.sampleweather.data.weather.remote.WeatherApiClient
import com.optimasolution.sampleweather.domain.util.Resource
import com.optimasolution.sampleweather.domain.weather.model.Weather
import com.optimasolution.sampleweather.domain.weather.model.map
import com.optimasolution.sampleweather.util.addBearerToken
import kotlinx.coroutines.flow.Flow

class WeatherDataStore(private val webService: WeatherApiClient): WeatherRepository {

    override suspend fun getAllWeather(apiKey: String, token: String): Flow<Resource<List<Weather>>> =
        webService.getAllWeather(apiKey, token.addBearerToken()).call().mapToDomain { it.map() }
}