package com.olucasmoro.movieapp.feature_album.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.databinding.FragmentMovieDetailBinding
import com.olucasmoro.movieapp.feature_album.data.model.MovieDetail
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Toast
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment(), View.OnClickListener {

    private val viewModel: MovieDetailViewModel by viewModel()
    private val binding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    private val args: MovieDetailFragmentArgs by navArgs()

    private lateinit var mSharedPreferences: SecurityPreferences

    private var isWatchlist = false
    private var movieId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mSharedPreferences = SecurityPreferences(requireContext())

        binding.ivFavorite.setOnClickListener(this)

        movieId = args.movieId.toInt()
        isWatchlist = args.watchlist.toBoolean()
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
                            this.movieId = movie.id.toInt()
                            true
                        }
                    }
                    is CallResults.Error -> {
                        Toast.toastDisplay(
                            requireContext(),
                            Constants.MESSAGE.FAILURE_CONNECTION
                        )
                    }
                }
            }
        }
    }

    private fun updateScreen(movie: MovieDetail) {
        binding.tvDescriptionTitle.text = movie.original_title
        binding.tvVoteAverage.text = movie.vote_average
        binding.tvGnreValue.text = movie.release_date
        binding.tvDescriptionValue.text = movie.overview

        if (isWatchlist) {
            binding.ivFavorite.setImageResource(R.drawable.ic_favorite_fill)
        }

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + movie.poster_path)
            .into(binding.ivPoster)

        Picasso.get().load(Constants.API.BASE_URL_IMAGE + movie.backdrop_path)
            .into(binding.ivBackdrop)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.ivFavorite -> {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_fill)
                val accountId = mSharedPreferences.get(Constants.AUTHENTICATION.USER_ID).toInt()
                val sessionId = mSharedPreferences.get(Constants.AUTHENTICATION.SESSION_ID)
                viewModel.addWatchlist(
                    accountId = accountId,
                    sessionId = sessionId,
                    movieId = movieId
                )
                if (!isWatchlist) {
                    Toast.toastDisplay(requireContext(), "Add to watchlist")
                }

            }

        }

    }
}
