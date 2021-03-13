package com.olucasmoro.movieapp.feature_user.data.source

import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences

class UserLocalDataImpl(private val mSharedPreferences: SecurityPreferences) : UserLocalData {

    override fun store(key: String, value: String) {
        mSharedPreferences.store(key, value)
    }

    override fun get(key: String): String =
        mSharedPreferences.get(key)

}