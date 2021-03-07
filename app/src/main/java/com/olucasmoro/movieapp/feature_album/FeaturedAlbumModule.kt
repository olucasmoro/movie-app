package com.olucasmoro.movieapp.feature_album

import com.olucasmoro.movieapp.feature_album.data.remote.AlbumRemoteData
import com.olucasmoro.movieapp.feature_album.data.remote.AlbumRemoteDataImpl
import com.olucasmoro.movieapp.feature_album.data.remote.AlbumRepositoryImpl
import com.olucasmoro.movieapp.feature_album.data.remote.api.AlbumApiService
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository
import com.olucasmoro.movieapp.feature_album.domain.usecase.AlbumUseCase
import com.olucasmoro.movieapp.feature_album.presentation.Constants.API.BASE_URL
import com.olucasmoro.movieapp.feature_album.presentation.movielist.MovieListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    single<AlbumApiService> { get<Retrofit>().create(AlbumApiService::class.java) }
}

val useCaseModule = module {
    factory { AlbumUseCase(get()) }
}

val remoteDataModule = module {
    factory<AlbumRemoteData> { AlbumRemoteDataImpl(apiService = get()) }
}

val repositoryModule = module {
    factory<AlbumRepository> { AlbumRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel<MovieListViewModel> { MovieListViewModel(get()) }
}

val albumModules = listOf(
    retrofitModule,
    remoteDataModule,
    useCaseModule,
    repositoryModule,
    viewModelModule
)