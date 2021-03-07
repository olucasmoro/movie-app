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
import com.olucasmoro.movieapp.databinding.FragmentSearchBinding
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Auxiliary
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieSearchFragment : Fragment() {

    private val viewModel: MovieSearchViewModel by viewModel()

    private val binding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    private lateinit var searchAdapter: MovieSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


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

    private fun updateAdapter() {
        binding.rvSearchMovie.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
            adapter = searchAdapter
        }

    }
}