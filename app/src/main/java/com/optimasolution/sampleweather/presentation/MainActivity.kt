package com.optimasolution.sampleweather.presentation

import com.optimasolution.sampleweather.R
import com.optimasolution.sampleweather.base.BaseActivity
import com.optimasolution.sampleweather.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val pagerAdapter: BoardingViewPagerAdapter by lazy {
        BoardingViewPagerAdapter(
            context = this,
            listBoarding = getListItemBoarding()
        )
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupIntent() {}

    override fun setupUI() {
        binding.viewPager.adapter = pagerAdapter
    }

    override fun setupAction() {
        binding.btnNext.setOnClickListener {
            when(val position = binding.viewPager.currentItem) {
                pagerAdapter.count.minus(1) -> {
                    HomeActivity.start(this)
                    finish()
                }
                else -> binding.viewPager.currentItem = position.plus(1)
            }
        }
    }

    override fun setupProcess() {}

    override fun setupObserver() {}

    private fun getListItemBoarding(): List<BoardingItem> {
        return listOf(
            BoardingItem(
                label = "WEATHER FOR EACH CITY",
                isIndicatorOneSelected = true,
                isIndicatorTwoSelected = false,
                isIndicatorThreeSelected = false,
                image = R.drawable.img_weather_sunny_large
            ),
            BoardingItem(
                label = "EASY TO USE",
                isIndicatorOneSelected = false,
                isIndicatorTwoSelected = true,
                isIndicatorThreeSelected = false,
                image = R.drawable.img_weather_raining_large
            ),
            BoardingItem(
                label = "CHECK THE WEATHER ANYWHERE",
                isIndicatorOneSelected = false,
                isIndicatorTwoSelected = false,
                isIndicatorThreeSelected = true,
                image = R.drawable.img_weather_stormy_large
            )
        )
    }
}