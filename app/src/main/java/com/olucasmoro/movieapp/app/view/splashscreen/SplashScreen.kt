package com.olucasmoro.movieapp.app.view.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.olucasmoro.movieapp.MainActivity
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import java.util.*

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var timer = Timer()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed(
            {
                val mSharedPreferences = SecurityPreferences(requireContext())
                if (mSharedPreferences.get(Constants.AUTHENTICATION.SESSION_ID) != "") {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    onDestroy()
                } else {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
                }
            }, Constants.TIMER.SPLASH_TIMER
        )
    }
}