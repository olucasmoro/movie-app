package com.olucasmoro.movieapp.feature_user.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import com.olucasmoro.movieapp.feature_user.domain.usecase.UserUseCase

class SignUpViewModel(
    private val useCase: UserUseCase
) : ViewModel() {

    fun getToken(): LiveData<CallResults<TokenResponse?>> =
        useCase.getToken(Constants.API.API_KEY)

    fun createSessionWithLogin(
        username: String,
        password: String,
        requestToken: String
    ): LiveData<CallResults<TokenResponse?>> =
        useCase.createSessionWithLogin(Constants.API.API_KEY, username, password, requestToken)

    fun createSession(
        requestToken: String
    ): LiveData<CallResults<SessionResponse?>> {
        return useCase.createSession(Constants.API.API_KEY, requestToken)
    }

}