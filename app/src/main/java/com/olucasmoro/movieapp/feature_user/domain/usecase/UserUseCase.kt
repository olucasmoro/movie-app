package com.olucasmoro.movieapp.feature_user.domain.usecase

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import com.olucasmoro.movieapp.feature_user.domain.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
) {

    fun getToken(apiKey: String): LiveData<CallResults<TokenResponse?>> =
        repository.getToken(apiKey)

    fun createSession(
        apiKey: String,
        requestToken: String
    ): LiveData<CallResults<SessionResponse?>> = repository.createSession(apiKey, requestToken)

    fun saveUserFirebase(
        username: String,
        name: String,
        email: String,
        password: String,
        session_id: String
    ) = repository.saveUserFirebase(username, name, email, password, session_id)

    fun checkUserFirebase(username: String, password: String): Int =
        repository.checkUserFirebase(username, password)
}