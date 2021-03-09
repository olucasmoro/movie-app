package com.olucasmoro.movieapp.feature_user.data

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import com.olucasmoro.movieapp.feature_user.data.source.UserRemoteData
import com.olucasmoro.movieapp.feature_user.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteData: UserRemoteData
) : UserRepository {

    override fun getToken(apiKey: String): LiveData<CallResults<TokenResponse?>> {
        return remoteData.getToken(apiKey)
    }

    override fun createSessionWithLogin(
        apiKey: String,
        username: String,
        password: String,
        requestToken: String
    ): LiveData<CallResults<TokenResponse?>> {
        return remoteData.createSessionWithLogin(apiKey, username, password, requestToken)
    }

    override fun createSession(apiKey: String, requestToken: String): LiveData<CallResults<SessionResponse?>> {
        return remoteData.createSession(apiKey, requestToken)
    }
}