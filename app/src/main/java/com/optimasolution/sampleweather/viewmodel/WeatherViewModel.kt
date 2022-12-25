package com.optimasolution.sampleweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.optimasolution.sampleweather.base.BaseViewModel
import com.optimasolution.sampleweather.data.weather.model.HourlyForecastResponse
import com.optimasolution.sampleweather.data.weather.model.SevenDayForecastResponse
import com.optimasolution.sampleweather.domain.util.Resource
import com.optimasolution.sampleweather.domain.weather.WeatherUseCase
import com.optimasolution.sampleweather.util.collectResult
import io.reactivex.disposables.CompositeDisposable

class WeatherViewModel(
    private val useCase: WeatherUseCase,
    disposable: CompositeDisposable
): BaseViewModel(disposable) {

    private val _listHourlyForecast: MutableLiveData<Resource<List<HourlyForecastResponse>>> = MutableLiveData()
    val listHourlyForecast: LiveData<Resource<List<HourlyForecastResponse>>> get() = _listHourlyForecast

    private val _listSevenDayForecast: MutableLiveData<Resource<List<SevenDayForecastResponse>>> = MutableLiveData()
    val listSevenDayForecast: LiveData<Resource<List<SevenDayForecastResponse>>> get() = _listSevenDayForecast

    fun getHourlyForecast(apiKey: String, token: String, cityName: String) {
        _listHourlyForecast.value = Resource.Loading()
        viewModelScope.collectResult(_listHourlyForecast) {
            useCase.getHourlyForecast(apiKey, token, cityName)
        }
    }

    fun getSevenDayForecast(apiKey: String, token: String, cityName: String) {
        _listSevenDayForecast.value = Resource.Loading()
        viewModelScope.collectResult(_listSevenDayForecast) {
            useCase.getSevenDayForecast(apiKey, token, cityName)
        }
    }
}