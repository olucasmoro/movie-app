package com.olucasmoro.movieapp.feature_album.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.olucasmoro.movieapp.databinding.FragmentMovieDetailBinding
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Auxiliary
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModel()
    private val binding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    private val args: MovieDetailFragmentArgs by navArgs()

    private var isWatchlist = false
    private var isWatched = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        isWatchlist = args.watchlist.toBoolean()
//        updateScreen2(isWatchlist, isWatched)

        val movieId = args.movieId.toInt()
        getDetailMovie(movieId)

        return binding.root
    }

    private fun getDetailMovie(movieId: Int) {
        viewModel.detailMovie(movieId).observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movie ->
                            updateScreen(movie)
                            true
                        } ?: false
                    }
                    is CallResults.Error -> {
                        Auxiliary.toastDisplay(
                            requireContext(),
                            Constants.MESSAGE.FAILURE_CONNECTION
                        )
                        false
                    }
                }
            } ?: false
        }
    }

    private fun updateScreen(movie: MovieDetail) {
        binding.tvDescriptionTitle.text = movie.original_title
        binding.tvVoteAverage.text = movie.vote_average
        binding.tvGnreValue.text = movie.release_date
        binding.tvDescriptionValue.text = movie.overview

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + movie.poster_path)
            .into(binding.ivPoster)

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + movie.backdrop_path)
            .into(binding.ivBackdrop)
    }

    private fun updateScreen2(watchlist: Boolean, watched: Boolean) {
        binding.ivFavorite.isVisible = true
    }
}
