package com.olucasmoro.movieapp.feature_album.presentation.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.databinding.FragmentMovieListBinding
import com.olucasmoro.movieapp.feature_album.data.model.Movie
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.app.service.utils.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModel()

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieListBinding.inflate(layoutInflater)

        returnMovies()

        return binding.root
    }

    private fun returnMovies() {
        viewModel.moviesPopular.observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Error -> {
                        Toast.toastDisplay(
                            requireContext(),
                            Constants.MESSAGE.FAILURE_CONNECTION
                        )
                    }
                    is CallResults.Success -> {
                        response.data?.let { movies ->
                            val movieListAdapter2 = MovieListAdapter2(movies, findNavController())
                            movieListAdapter2.notifyDataSetChanged()
                            binding.rvPopular.apply {
                                layoutManager =
                                    LinearLayoutManager(
                                        requireContext(),
                                        RecyclerView.HORIZONTAL,
                                        false
                                    )
                                adapter = movieListAdapter2
                            }
                            true
                        }
                    }
                }
            }
        }

        viewModel.moviesNowPlaying.observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movies ->
                            defineAdapter(movies, Constants.TYPE.NOW_PLAYING)
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

        viewModel.moviesTopRated.observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movies ->
                            defineAdapter(movies, Constants.TYPE.TOP_RATED)
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

        viewModel.moviesUpcoming.observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movies ->
                            defineAdapter(movies, Constants.TYPE.UPCOMING)
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

    private fun defineAdapter(movies: List<Movie>, movieType: String) {
        movieListAdapter = MovieListAdapter(movies, findNavController())
        updateAdapter(movieType)
        movieListAdapter.notifyDataSetChanged()
    }

    private fun updateAdapter(movieType: String) {
        when (movieType) {
            Constants.TYPE.TOP_RATED -> binding.rvTopRated.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = movieListAdapter
            }
            Constants.TYPE.UPCOMING -> binding.rvUpcoming.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = movieListAdapter
            }
            Constants.TYPE.NOW_PLAYING -> binding.rvNowPlaying.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = movieListAdapter
            }

        }

    }

}