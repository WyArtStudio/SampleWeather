package com.optimasolution.sampleweather.util

import androidx.recyclerview.widget.DiffUtil
import com.optimasolution.sampleweather.data.weather.model.HourlyForecastResponse
import com.optimasolution.sampleweather.data.weather.model.SevenDayForecastResponse

object ObjectDiffUtil {
    val hourlyDiffUtil = object : DiffUtil.ItemCallback<HourlyForecastResponse>(){
        override fun areItemsTheSame(oldItem: HourlyForecastResponse, newItem: HourlyForecastResponse): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HourlyForecastResponse, newItem: HourlyForecastResponse): Boolean =
            oldItem == newItem
    }

    val sevenDaysDiffUtil = object : DiffUtil.ItemCallback<SevenDayForecastResponse>(){
        override fun areItemsTheSame(oldItem: SevenDayForecastResponse, newItem: SevenDayForecastResponse): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SevenDayForecastResponse, newItem: SevenDayForecastResponse): Boolean =
            oldItem == newItem
    }
}