package com.optimasolution.sampleweather.data.weather.model

import com.google.gson.annotations.SerializedName

data class SevenDayForecastResponse(

	@field:SerializedName("temp")
	val temp: String? = null,

	@field:SerializedName("cityName")
	val cityName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("day")
	val day: String? = null,

	@field:SerializedName("percent")
	val percent: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
