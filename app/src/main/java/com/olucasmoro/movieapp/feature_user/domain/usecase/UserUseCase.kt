package com.olucasmoro.movieapp.feature_user.domain.usecase

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import com.olucasmoro.movieapp.feature_user.domain.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
) {

    fun getToken(apiKey: String): LiveData<CallResults<TokenResponse?>> {
        return repository.getToken(apiKey)
    }

    fun createSessionWithLogin(apiKey: String, username: String, password: String, requestToken: String): LiveData<CallResults<TokenResponse?>> {
        return repository.createSessionWithLogin(apiKey, username, password, requestToken)
    }

    fun createSession(apiKey: String, requestToken: String): LiveData<CallResults<SessionResponse?>> {
        return repository.createSession(apiKey, requestToken)
    }
}