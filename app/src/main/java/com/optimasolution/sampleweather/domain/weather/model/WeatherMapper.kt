package com.optimasolution.sampleweather.domain.weather.model

import com.optimasolution.sampleweather.data.weather.model.WeatherResponse
import com.optimasolution.sampleweather.util.orZero

fun WeatherResponse.map(): Weather =
    Weather(
        temperature = temperature.orZero(),
        createdAt = createdAt.orEmpty(),
        id = id.orZero()
    )

fun List<WeatherResponse>.map(): List<Weather> =
    this.map { it.map() }