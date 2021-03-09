package com.olucasmoro.movieapp.feature_user.domain.repository

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse

interface UserRepository {

    fun getToken(apiKey: String): LiveData<CallResults<TokenResponse?>>

    fun createSessionWithLogin(apiKey: String, username: String, password: String, requestToken: String): LiveData<CallResults<TokenResponse?>>

    fun createSession(apiKey: String, requestToken: String): LiveData<CallResults<SessionResponse?>>
}