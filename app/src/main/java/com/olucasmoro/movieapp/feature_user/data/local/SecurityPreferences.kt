package com.olucasmoro.movieapp.feature_user.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SecurityPreferences(context: Context) {

    private val mPreferences: SharedPreferences =
        context.getSharedPreferences("movieShared", Context.MODE_PRIVATE)

    fun store(key: String, value: String) {
        mPreferences.edit().putString(key, value).apply()
    }

    fun remove(key: String) {
        mPreferences.edit().remove(key).apply()
    }

    fun get(key: String): String {
        return mPreferences.getString(key, "") ?: ""
    }

    fun saveUser(user: User) {
        val prefsEditor = mPreferences.edit();
        val gson = Gson()
        val userJson = gson.toJson(user)
        prefsEditor.putString("User", userJson);
        prefsEditor.apply();
    }

    fun getUser(key: String): User {
        val gson = Gson()
        val json: String? = mPreferences.getString(key, "")
        return gson.fromJson(json, User::class.java)
    }

}