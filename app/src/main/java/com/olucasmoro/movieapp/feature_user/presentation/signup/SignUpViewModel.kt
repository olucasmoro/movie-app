package com.olucasmoro.movieapp.feature_user.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.User
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import com.olucasmoro.movieapp.feature_user.domain.usecase.UserUseCase

class SignUpViewModel(
    private val useCase: UserUseCase
) : ViewModel() {

    fun getToken(): LiveData<CallResults<TokenResponse?>> =
        useCase.getToken(Constants.API.API_KEY)

    fun store(key: String, value: String) = useCase.store(key, value)

}