package com.olucasmoro.movieapp.app.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.olucasmoro.movieapp.R
import kotlin.concurrent.schedule
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import java.util.*

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var timer = Timer()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val mSharedPreferences = SecurityPreferences(requireContext())

        timer.schedule(Constants.TIMER.SPLASH_TIMER) {
//            if (mSharedPreferences.get(Constants.AUTHENTICATION.IS_LOGGED) != "") {
//                startActivity(Intent(requireContext(), MainActivity::class.java))
//                onDestroy()
//            } else {
//            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
//            }
        }
    }
}