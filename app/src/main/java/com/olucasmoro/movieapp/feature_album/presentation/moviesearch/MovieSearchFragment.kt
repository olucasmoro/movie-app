package com.olucasmoro.movieapp.feature_album.presentation.moviesearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.databinding.FragmentMovieSearchBinding
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Toast
import com.olucasmoro.movieapp.app.service.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieSearchFragment : Fragment() {

    private val viewModel: MovieSearchViewModel by viewModel()

    private var _binding: FragmentMovieSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchAdapter: MovieSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieSearchBinding.inflate(layoutInflater)

        binding.editQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(str: Editable?) {
                searchMovies(str.toString())
            }

        })

        return binding.root
    }

    private fun searchMovies(str: String) {

        viewModel.searchMovies(str).observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movie ->
                            searchAdapter = MovieSearchAdapter(movie, findNavController())
                            updateAdapter()
                            searchAdapter.notifyDataSetChanged()
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

    private fun updateAdapter() {
        binding.rvSearchMovie.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
            adapter = searchAdapter
        }

    }
}