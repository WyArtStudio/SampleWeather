package com.optimasolution.sampleweather.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.optimasolution.sampleweather.presentation.fragment.JakartaWeatherFragment
import com.optimasolution.sampleweather.presentation.fragment.JayapuraWeatherFragment
import com.optimasolution.sampleweather.presentation.fragment.MakassarWeatherFragment

class WeatherViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {

        val fragment: Fragment = when (position) {
            0 -> JakartaWeatherFragment()
            1 -> MakassarWeatherFragment()
            else -> JayapuraWeatherFragment()
        }

        return fragment
    }
}