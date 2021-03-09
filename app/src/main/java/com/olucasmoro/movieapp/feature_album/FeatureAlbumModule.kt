package com.olucasmoro.movieapp.feature_album

import com.olucasmoro.movieapp.feature_album.data.source.AlbumRemoteData
import com.olucasmoro.movieapp.feature_album.data.source.AlbumRemoteDataImpl
import com.olucasmoro.movieapp.feature_album.data.AlbumRepositoryImpl
import com.olucasmoro.movieapp.feature_album.data.api.AlbumApiService
import com.olucasmoro.movieapp.feature_album.domain.repository.AlbumRepository
import com.olucasmoro.movieapp.feature_album.domain.usecase.AlbumUseCase
import com.olucasmoro.movieapp.feature_album.presentation.moviedetail.MovieDetailViewModel
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants.API.BASE_URL
import com.olucasmoro.movieapp.feature_album.presentation.movielist.MovieListViewModel
import com.olucasmoro.movieapp.feature_album.presentation.moviesearch.MovieSearchViewModel
import com.olucasmoro.movieapp.feature_user.data.api.UserApiService
import com.olucasmoro.movieapp.feature_watchlist.data.api.WatchlistApiService
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
    single<WatchlistApiService> { get<Retrofit>().create(WatchlistApiService::class.java) }
    single<UserApiService> { get<Retrofit>().create(UserApiService::class.java) }
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
    viewModel<MovieDetailViewModel> { MovieDetailViewModel(get()) }
    viewModel<MovieSearchViewModel> { MovieSearchViewModel(get()) }
}

val albumModules = listOf(
    retrofitModule,
    remoteDataModule,
    useCaseModule,
    repositoryModule,
    viewModelModule
)