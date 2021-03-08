package com.olucasmoro.movieapp.feature_watchlist

import com.olucasmoro.movieapp.feature_watchlist.data.WatchlistRepositoryImpl
import com.olucasmoro.movieapp.feature_watchlist.data.source.WatchlistRemoteData
import com.olucasmoro.movieapp.feature_watchlist.data.source.WatchlistRemoteDataImpl
import com.olucasmoro.movieapp.feature_watchlist.domain.repository.WatchlistRepository
import com.olucasmoro.movieapp.feature_watchlist.domain.usecase.WatchlistUseCase
import com.olucasmoro.movieapp.feature_watchlist.presentation.WatchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val watchlistModule = module {
    factory { WatchlistUseCase(get()) }
}

val watchlistRemoteDataModule = module {
    factory<WatchlistRemoteData> { WatchlistRemoteDataImpl(get()) }
}

val watchlistRepositoryModule = module {
    factory<WatchlistRepository> { WatchlistRepositoryImpl(get()) }
}

val watchlistViewModelModule = module {
    viewModel<WatchListViewModel> { WatchListViewModel(get()) }
}

val watchlistModules = listOf(
    watchlistRemoteDataModule,
    watchlistModule,
    watchlistRepositoryModule,
    watchlistViewModelModule,
)