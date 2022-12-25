package com.optimasolution.sampleweather.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.optimasolution.sampleweather.base.BaseAdapter
import com.optimasolution.sampleweather.base.BaseViewHolder
import com.optimasolution.sampleweather.data.weather.model.SevenDayForecastResponse
import com.optimasolution.sampleweather.databinding.ItemSevenDaysForecastBinding
import com.optimasolution.sampleweather.presentation.SevenDaysForecastAdapter.ListViewHolder
import com.optimasolution.sampleweather.util.ObjectDiffUtil
import com.optimasolution.sampleweather.util.setImageWeather

class SevenDaysForecastAdapter(private val onItemClicked: ((SevenDayForecastResponse) -> Unit)? = null)
    : BaseAdapter<SevenDayForecastResponse, ItemSevenDaysForecastBinding, ListViewHolder>
    (ObjectDiffUtil.sevenDaysDiffUtil) {

    inner class ListViewHolder(mBinding: ItemSevenDaysForecastBinding):
        BaseViewHolder<SevenDayForecastResponse>(mBinding) {
        override fun bind(data: SevenDayForecastResponse) {
            with(binding as ItemSevenDaysForecastBinding){
                imgWeather.setImageWeather(data.status.toString())
                tvDay.text = data.day
                tvPercent.text = "${data.percent}%"
                tvTemperature.text = data.temp.toString()
                root.setOnClickListener {
                    onItemClicked?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemSevenDaysForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}