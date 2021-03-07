package com.olucasmoro.movieapp.feature_album.presentation.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.databinding.FragmentMovieListBinding
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(), View.OnClickListener {

    private val viewModel: MovieListViewModel by viewModel()
    private val binding by lazy {
        FragmentMovieListBinding.inflate(layoutInflater)
    }

    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        searchMovies()

        setListeners()

        return binding.root
    }

    /**
     *  Observamos a variável 'moviesPopular' da classe MovieListViewModel e enviamos quaisquer
     *  alterações ao adaptador para mostrar a lista de filmes.
     */
    private fun searchMovies() {
        viewModel.moviesPopular().observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Error -> {
//                        Snackbar.make(
//                            binding.coordinatorLayout,
//                            resultado.exception.message.toString(),
//                            Snackbar.LENGTH_SHORT
//                        ).show()
                        false
                    }
                    is CallResults.Success -> {
                        response.data?.let { movie ->
                            movieListAdapter = MovieListAdapter(movie)
                            updateAdapter(Constants.MOVIETYPE.POPULAR)
                            movieListAdapter.notifyDataSetChanged()
                            true
                        } ?: false
                    }
                }
            } ?: false
        }

        viewModel.moviesNowPlaying().observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movie ->
                            movieListAdapter = MovieListAdapter(movie)
                            updateAdapter(Constants.MOVIETYPE.NOW_PLAYING)
                            movieListAdapter.notifyDataSetChanged()
                            true
                        } ?: false
                    }
                    is CallResults.Error -> {
//                        Snackbar.make(
//                            binding.coordinatorLayout,
//                            resultado.exception.message.toString(),
//                            Snackbar.LENGTH_SHORT
//                        ).show()
                        false
                    }
                }
            } ?: false
        }

        viewModel.moviesTopRated().observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movie ->
                            movieListAdapter = MovieListAdapter(movie)
                            updateAdapter(Constants.MOVIETYPE.TOP_RATED)
                            movieListAdapter.notifyDataSetChanged()
                            true
                        } ?: false
                    }
                    is CallResults.Error -> {
//                        Snackbar.make(
//                            binding.coordinatorLayout,
//                            resultado.exception.message.toString(),
//                            Snackbar.LENGTH_SHORT
//                        ).show()
                        false
                    }
                }
            } ?: false
        }

        viewModel.moviesUpcoming().observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movie ->
                            movieListAdapter = MovieListAdapter(movie)
                            updateAdapter(Constants.MOVIETYPE.UPCOMING)
                            movieListAdapter.notifyDataSetChanged()
                            true
                        } ?: false
                    }
                    is CallResults.Error -> {
//                        Snackbar.make(
//                            binding.coordinatorLayout,
//                            resultado.exception.message.toString(),
//                            Snackbar.LENGTH_SHORT
//                        ).show()
                        false
                    }
                }
            } ?: false
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            //binding.tvAllPopular -> findNavController().navigate(MovieAlbumFragmentDirections.actionNavigationHomeToMovieAllFragment())
        }
    }

    private fun updateAdapter(movieType: String) {
        when (movieType) {
            Constants.MOVIETYPE.POPULAR -> binding.rvPopular.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = movieListAdapter
            }
            Constants.MOVIETYPE.TOP_RATED -> binding.rvTopRated.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = movieListAdapter
            }
            Constants.MOVIETYPE.UPCOMING -> binding.rvUpcoming.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = movieListAdapter
            }
            Constants.MOVIETYPE.NOW_PLAYING -> binding.rvNowPlaying.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = movieListAdapter
            }


        }

    }

    private fun setListeners() {
        binding.tvAllPopular.setOnClickListener(this)
        binding.tvAllTopRated.setOnClickListener(this)
        binding.tvAllNowPlaying.setOnClickListener(this)
        binding.tvAllUpcoming.setOnClickListener(this)
    }
}