package com.olucasmoro.movieapp.app.view.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.olucasmoro.movieapp.MainActivity
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.databinding.FragmentSplashBinding
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var mSharedPreferences: SecurityPreferences

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mSharedPreferences = SecurityPreferences(requireContext())

        Handler().postDelayed(
            {
                if (mSharedPreferences.get(Constants.AUTHENTICATION.SESSION_ID) != "") {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    onDestroy()
                } else {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                    onDestroy()
                }
            }, Constants.TIMER.SPLASH_TIMER
        )
    }

}