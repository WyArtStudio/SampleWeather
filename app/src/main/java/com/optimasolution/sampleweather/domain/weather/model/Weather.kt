package com.optimasolution.sampleweather.domain.weather.model

import android.os.Parcelable
import com.optimasolution.sampleweather.util.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
	val temperature: Int = 0,
	val createdAt: String = emptyString(),
	val id: Int = 0
): Parcelable
