package com.olucasmoro.movieapp

import android.app.Application
import com.olucasmoro.movieapp.feature_album.albumModules
import com.olucasmoro.movieapp.feature_user.userModules
import com.olucasmoro.movieapp.feature_watchlist.watchlistModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(albumModules + watchlistModules + userModules)
        }
    }
}