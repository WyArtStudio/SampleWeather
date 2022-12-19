package com.optimasolution.sampleweather.presentation

import android.content.Context
import android.content.Intent
import com.optimasolution.sampleweather.base.BaseActivity
import com.optimasolution.sampleweather.databinding.ActivityMainBinding
import com.optimasolution.sampleweather.domain.weather.model.Weather
import com.optimasolution.sampleweather.util.API_KEY
import com.optimasolution.sampleweather.util.TOKEN
import com.optimasolution.sampleweather.util.observe
import com.optimasolution.sampleweather.util.showShortToast
import com.optimasolution.sampleweather.viewmodel.WeatherViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val weatherViewModel: WeatherViewModel by inject()

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupIntent() {}

    override fun setupUI() {}

    override fun setupAction() {}

    override fun setupProcess() {
        weatherViewModel.getListWeather(API_KEY, TOKEN)
    }

    override fun setupObserver() {
        weatherViewModel.listWeather.observe(this,
            onLoading = {
                showCancelableDialog("Loading...")
            },
            onError = {
                showShortToast("Error: $it")
            },
            onSuccess = {
                dismissDialog()
                val weather = it.first()
                updateUI(weather)
            }
        )
    }

    private fun updateUI(weather: Weather) {
        with(binding) {
            tvId.text = "Id: ${weather.id}"
            tvCreatedAt.text = "Created at: ${weather.createdAt}"
            tvTemperature.text = "Temperature: ${weather.temperature} celcius"
        }
    }
}