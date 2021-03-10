package com.olucasmoro.movieapp.feature_user.domain.repository

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse

interface UserRepository {

    fun getToken(apiKey: String): LiveData<CallResults<TokenResponse?>>

    fun createSession(apiKey: String, requestToken: String): LiveData<CallResults<SessionResponse?>>

    fun saveUserFirebase(
        username: String,
        name: String,
        email: String,
        password: String,
        session_id: String
    )

    fun checkUserFirebase(username: String, password: String): Int
}