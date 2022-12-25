package com.optimasolution.sampleweather.presentation

import androidx.annotation.DrawableRes

data class BoardingItem(
    val label: String = "",
    val isIndicatorOneSelected: Boolean = false,
    val isIndicatorTwoSelected: Boolean = false,
    val isIndicatorThreeSelected: Boolean = false,
    @DrawableRes
    val image: Int = 0
)
