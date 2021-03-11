package com.olucasmoro.movieapp.app.view.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.databinding.ItemSlideBinding

class OnBoardingAdapter(private var context: Context) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    var images = intArrayOf(
        R.drawable.tmdb_logo,
        R.drawable.image_slide,
        R.drawable.image_slide_2,
    )

    private var headings = intArrayOf(
        R.string.slide_title_1,
        R.string.slide_title_2,
        R.string.slide_title_3,
    )

    var descriptions = intArrayOf(
        R.string.slide_desc_1,
        R.string.slide_desc_2,
        R.string.slide_desc_3,
    )

    override fun getCount(): Int {
        return headings.size
    }

    /**
     * Returns a boolean if the view is the one we are using
     * @param view
     * @param object
     * @return
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


        val view = ItemSlideBinding.inflate(
            layoutInflater,
            container,
            false
        )

//        val view: View = layoutInflater.inflate(R.layout.item_slide, container, false)

        view.sliderImg.setImageResource(images[position])
        view.slideHeading.setText(headings[position])
        view.sliderDesc.setText(descriptions[position])
        container.addView(view.root)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}