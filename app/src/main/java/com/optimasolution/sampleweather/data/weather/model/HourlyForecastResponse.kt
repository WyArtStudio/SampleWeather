package com.optimasolution.sampleweather.data.weather.model

import com.google.gson.annotations.SerializedName

data class HourlyForecastResponse(

	@field:SerializedName("temp")
	val temp: Int? = null,

	@field:SerializedName("cityName")
	val cityName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
