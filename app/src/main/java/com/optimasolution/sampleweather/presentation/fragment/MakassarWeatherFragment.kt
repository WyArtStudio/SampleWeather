package com.optimasolution.sampleweather.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimasolution.sampleweather.base.BaseFragment
import com.optimasolution.sampleweather.databinding.FragmentMakassarWeatherBinding
import com.optimasolution.sampleweather.presentation.HourlyForecastAdapter
import com.optimasolution.sampleweather.presentation.SevenDaysForecastAdapter
import com.optimasolution.sampleweather.util.API_KEY
import com.optimasolution.sampleweather.util.TOKEN
import com.optimasolution.sampleweather.util.getCurrentDateTime
import com.optimasolution.sampleweather.util.gone
import com.optimasolution.sampleweather.util.hasNetwork
import com.optimasolution.sampleweather.util.observe
import com.optimasolution.sampleweather.util.showShortToast
import com.optimasolution.sampleweather.util.toString
import com.optimasolution.sampleweather.util.visible
import com.optimasolution.sampleweather.viewmodel.WeatherViewModel
import org.koin.android.ext.android.inject

class MakassarWeatherFragment : BaseFragment<FragmentMakassarWeatherBinding>() {
    private val weatherViewModel: WeatherViewModel by inject()
    private val hourlyForecastAdapter = HourlyForecastAdapter()
    private val sevenDaysForecastAdapter = SevenDaysForecastAdapter()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMakassarWeatherBinding {
        return FragmentMakassarWeatherBinding.inflate(layoutInflater, container, false)
    }

    override fun setupIntent() {}

    override fun setupUI() {
        binding.rvHourlyForecast.apply {
            adapter = hourlyForecastAdapter
            layoutManager = GridLayoutManager(requireContext(), 11)
        }

        binding.rvSevenDaysForecast.apply {
            adapter = sevenDaysForecastAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        val date = getCurrentDateTime()
        binding.tvDate.text = date.toString("d MMMM yyyy")

        loadData()
    }

    override fun setupAction() {
        binding.btnRetry.setOnClickListener { loadData() }
    }

    override fun setupProcess() {}

    private fun loadData() {
        if (hasNetwork(requireContext()) == true) {
            try {
                showOnlineMode()
                weatherViewModel.getHourlyForecast(API_KEY, TOKEN, "MAKASSAR")
                weatherViewModel.getSevenDayForecast(API_KEY, TOKEN, "MAKASSAR")
            } catch (e: Exception) {
                showOfflineMode()
            }
        } else {
            showOfflineMode()
        }
    }

    private fun showOfflineMode() {
        binding.containerOffline.visible()
    }

    private fun showOnlineMode() {
        binding.containerOffline.gone()
    }

    override fun setupObserver() {
        weatherViewModel.listHourlyForecast.observe(this,
            onLoading = {
                showCancelableDialog("Loading...")
            },
            onError = {
                dismissDialog()
                requireContext().showShortToast("Error: $it")
            },
            onSuccess = {
                dismissDialog()
                val list = it.sortedBy { response ->
                    response.id
                }
                hourlyForecastAdapter.submitList(list)
            }
        )

        weatherViewModel.listSevenDayForecast.observe(this,
            onLoading = {
                showCancelableDialog("Loading...")
            },
            onError = {
                dismissDialog()
                requireContext().showShortToast("Error: $it")
            },
            onSuccess = {
                dismissDialog()
                val list = it.sortedBy { id }
                sevenDaysForecastAdapter.submitList(list)
            }
        )
    }
}