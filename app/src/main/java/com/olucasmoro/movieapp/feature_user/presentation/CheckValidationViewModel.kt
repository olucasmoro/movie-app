package com.olucasmoro.movieapp.feature_user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.model.SessionResponse
import com.olucasmoro.movieapp.feature_user.data.model.TokenResponse
import com.olucasmoro.movieapp.feature_user.domain.usecase.UserUseCase

class CheckValidationViewModel(
    private val useCase: UserUseCase
) : ViewModel() {

    fun createSession(
        requestToken: String
    ): LiveData<CallResults<SessionResponse?>> {
        return useCase.createSession(Constants.API.API_KEY, requestToken)
    }

}