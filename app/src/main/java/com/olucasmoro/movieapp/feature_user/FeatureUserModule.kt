package com.olucasmoro.movieapp.feature_user

import com.olucasmoro.movieapp.feature_user.data.UserRepositoryImpl
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import com.olucasmoro.movieapp.feature_user.data.source.UserLocalData
import com.olucasmoro.movieapp.feature_user.data.source.UserLocalDataImpl
import com.olucasmoro.movieapp.feature_user.data.source.UserRemoteData
import com.olucasmoro.movieapp.feature_user.data.source.UserRemoteDataImpl
import com.olucasmoro.movieapp.feature_user.domain.repository.UserRepository
import com.olucasmoro.movieapp.feature_user.domain.usecase.UserUseCase
import com.olucasmoro.movieapp.feature_user.presentation.CheckValidationViewModel
import com.olucasmoro.movieapp.feature_user.presentation.login.LoginViewModel
import com.olucasmoro.movieapp.feature_user.presentation.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cache = module {
    single<SecurityPreferences> { SecurityPreferences(get()) }
}

val useCaseModule = module {
    factory { UserUseCase(get()) }
}

val remoteDataModule = module {
    factory<UserRemoteData> { UserRemoteDataImpl(apiService = get()) }
    factory<UserLocalData> { UserLocalDataImpl(mSharedPreferences = get()) }
}

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<SignUpViewModel> { SignUpViewModel(get()) }
    viewModel<CheckValidationViewModel> { CheckValidationViewModel(get()) }
}

val userModules = listOf(
    remoteDataModule,
    cache,
    useCaseModule,
    repositoryModule,
    viewModelModule
)