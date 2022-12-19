package com.optimasolution.sampleweather.data.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@field:SerializedName("temperature")
	val temperature: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
