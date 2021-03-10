package com.olucasmoro.movieapp.app.view.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.olucasmoro.movieapp.MainActivity
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.app.InitialActivity
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences

class SplashScreen : AppCompatActivity() {
    private lateinit var mSharedPreferences: SecurityPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        mSharedPreferences = SecurityPreferences(applicationContext)

        Handler().postDelayed(
            {
                if (mSharedPreferences.get(Constants.AUTHENTICATION.SESSION_ID) != "") {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    onDestroy()
                } else {
                    startActivity(Intent(applicationContext, InitialActivity::class.java))
                }
            }, Constants.TIMER.SPLASH_TIMER
        )
    }
}