package com.olucasmoro.movieapp.app.view.onboarding

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.databinding.FragmentOnBoardingBinding
import com.olucasmoro.movieapp.app.service.utils.Constants

class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding), View.OnClickListener {

    private lateinit var sliderAdapter: OnBoardingAdapter
    private var currentPosition = 0
    private var dots = ArrayList<TextView>(4)

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val view = binding.root

        sliderAdapter = OnBoardingAdapter(requireContext())
        binding.slider.adapter = sliderAdapter
        addDots(0)

        binding.slider.addOnPageChangeListener(changeListener)

        setListeners()

        return view
    }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnStarted -> skipSlides()
            binding.btnSkip -> skipSlides()
            binding.btnNext -> nextSlide()
        }
    }

    private fun setListeners() {
        binding.btnStarted.setOnClickListener(this)
        binding.btnSkip.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
    }

    private fun addDots(position: Int) {
        binding.dotsLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] =
                TextView(requireContext())
            dots[i].text = Html.fromHtml("&#8226;")
            dots[i].textSize = 35f
            binding.dotsLayout.addView(dots[i])
        }
        if (dots.isNotEmpty()) {
            dots[position].setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
        }
    }

    private fun nextSlide() {
        binding.slider.currentItem = currentPosition + 1
    }

    private fun skipSlides() {
        findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment())
    }

    private var changeListener: ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            addDots(position)
            currentPosition = position
            if (position != Constants.DOTS.POSITION_LAST_DOTS) {
                binding.btnStarted.isVisible = false
                binding.btnSkip.isVisible = true
            } else {
                binding.btnStarted.isVisible = true
                binding.btnSkip.isVisible = false
                binding.btnNext.isVisible = false
            }
        }

        override fun onPageScrolled(position: Int, posOffset: Float, posOffsetPixels: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

}