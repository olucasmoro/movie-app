package com.olucasmoro.movieapp.feature_user.data

import androidx.lifecycle.LiveData
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import com.olucasmoro.movieapp.feature_user.data.source.UserRemoteData
import com.olucasmoro.movieapp.feature_user.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteData: UserRemoteData
//    private val localData: UserLocalData
) : UserRepository {

    override fun getToken(apiKey: String): LiveData<CallResults<TokenResponse?>> {
        return remoteData.getToken(apiKey)
    }

    override fun createSession(
        apiKey: String,
        requestToken: String
    ): LiveData<CallResults<SessionResponse?>> {
        return remoteData.createSession(apiKey, requestToken)
    }

    override fun saveUserFirebase(
        username: String,
        name: String,
        email: String,
        password: String,
        session_id: String
    ) {
        return remoteData.saveUserFirebase(username, name, email, password, session_id)
    }

    override fun checkUserFirebase(username: String, password: String): Int =
        remoteData.checkUserFirebase(username, password)
}