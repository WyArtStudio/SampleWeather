package com.optimasolution.sampleweather.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.optimasolution.sampleweather.base.BaseAdapter
import com.optimasolution.sampleweather.base.BaseViewHolder
import com.optimasolution.sampleweather.data.weather.model.HourlyForecastResponse
import com.optimasolution.sampleweather.databinding.ItemHourlyForecastBinding
import com.optimasolution.sampleweather.presentation.HourlyForecastAdapter.ListViewHolder
import com.optimasolution.sampleweather.util.ObjectDiffUtil
import com.optimasolution.sampleweather.util.setImageWeather

class HourlyForecastAdapter(private val onItemClicked: ((HourlyForecastResponse) -> Unit)? = null)
    : BaseAdapter<HourlyForecastResponse, ItemHourlyForecastBinding, ListViewHolder>
    (ObjectDiffUtil.hourlyDiffUtil) {

    inner class ListViewHolder(mBinding: ItemHourlyForecastBinding):
        BaseViewHolder<HourlyForecastResponse>(mBinding) {
        override fun bind(data: HourlyForecastResponse) {
            with(binding as ItemHourlyForecastBinding){
                imgWeather.setImageWeather(data.status.toString())
                tvHour.text = data.time
                tvTemperature.text = "${data.temp.toString()}°"
                root.setOnClickListener {
                    onItemClicked?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemHourlyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}