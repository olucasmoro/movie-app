package com.olucasmoro.movieapp.feature_user

import com.olucasmoro.movieapp.feature_user.data.UserRepositoryImpl
import com.olucasmoro.movieapp.feature_user.data.source.UserRemoteData
import com.olucasmoro.movieapp.feature_user.data.source.UserRemoteDataImpl
import com.olucasmoro.movieapp.feature_user.domain.repository.UserRepository
import com.olucasmoro.movieapp.feature_user.domain.usecase.UserUseCase
import com.olucasmoro.movieapp.feature_user.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory { UserUseCase(get()) }
}

val remoteDataModule = module {
    factory<UserRemoteData> { UserRemoteDataImpl(apiService = get()) }
}

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
}

val userModules = listOf(
    remoteDataModule,
    useCaseModule,
    repositoryModule,
    viewModelModule
)