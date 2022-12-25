package com.optimasolution.sampleweather.presentation

import android.content.Context
import android.content.Intent
import com.optimasolution.sampleweather.base.BaseActivity
import com.optimasolution.sampleweather.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getViewBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun setupIntent() {}

    override fun setupUI() {
        binding.viewPager.adapter = WeatherViewPagerAdapter(supportFragmentManager)
    }

    override fun setupAction() {}

    override fun setupProcess() {}

    override fun setupObserver() {}
}