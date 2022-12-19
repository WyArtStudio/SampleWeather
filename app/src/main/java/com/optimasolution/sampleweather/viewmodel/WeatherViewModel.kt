package com.optimasolution.sampleweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.optimasolution.sampleweather.base.BaseViewModel
import com.optimasolution.sampleweather.domain.util.Resource
import com.optimasolution.sampleweather.domain.weather.WeatherUseCase
import com.optimasolution.sampleweather.domain.weather.model.Weather
import com.optimasolution.sampleweather.util.collectResult
import io.reactivex.disposables.CompositeDisposable

class WeatherViewModel(
    private val useCase: WeatherUseCase,
    disposable: CompositeDisposable
): BaseViewModel(disposable) {
    private val _listWeather: MutableLiveData<Resource<List<Weather>>> = MutableLiveData()
    val listWeather: LiveData<Resource<List<Weather>>> get() = _listWeather

    fun getListWeather(apiKey: String, token: String) {
        _listWeather.value = Resource.Loading()
        viewModelScope.collectResult(_listWeather) {
            useCase.getAllWeather(apiKey, token)
        }
    }
}