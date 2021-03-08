package com.olucasmoro.movieapp.app.view.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.olucasmoro.movieapp.R
import kotlinx.android.synthetic.main.item_slide.view.*

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

        val view: View = layoutInflater.inflate(R.layout.item_slide, container, false)

        view.slider_img.setImageResource(images[position])
        view.slide_heading.setText(headings[position])
        view.slider_desc.setText(descriptions[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}