package com.optimasolution.sampleweather.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.optimasolution.sampleweather.R
import com.optimasolution.sampleweather.databinding.LayoutItemBoardingBinding

class BoardingViewPagerAdapter(
    private val context: Context,
    private var listBoarding: List<BoardingItem>,
    private val onNextClicked: ((Int) -> Unit)?
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = listBoarding.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: LayoutItemBoardingBinding = LayoutItemBoardingBinding.inflate(
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        )

        val layoutScreen: View = binding.root
        val item = listBoarding[position]

        with(binding) {
            item.apply {
                tvLabel.text = label
                imgIndicatorOne.setIndicatorSelection(isIndicatorOneSelected)
                imgIndicatorTwo.setIndicatorSelection(isIndicatorTwoSelected)
                imgIndicatorThree.setIndicatorSelection(isIndicatorThreeSelected)
                imgWeather.setImageResource(image)

                btnNext.setOnClickListener {
                    onNextClicked?.invoke(position)
                }
            }
        }

        container.addView(layoutScreen)

        return layoutScreen
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    private fun ImageView.setIndicatorSelection(isIndicatorSelected: Boolean) {
        this.setImageResource(
            if (isIndicatorSelected) R.drawable.ic_dots_selected
            else R.drawable.ic_dots_unselected
        )
    }
}