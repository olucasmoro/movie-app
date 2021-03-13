package com.olucasmoro.movieapp.feature_user.data.source

interface UserLocalData {

    fun store(key: String, value: String)

    fun get(key: String): String
}