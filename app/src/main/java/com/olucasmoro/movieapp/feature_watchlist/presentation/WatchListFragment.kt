package com.olucasmoro.movieapp.feature_watchlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.olucasmoro.movieapp.databinding.FragmentWatchlistBinding
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Auxiliary
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchListFragment : Fragment() {

    private val viewModel: WatchListViewModel by viewModel()
    private val binding by lazy {
        FragmentWatchlistBinding.inflate(layoutInflater)
    }

    private lateinit var watchlistAdapter: WatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val userId = 10147690
        val sessionId = "087a37f6e78d5054652c9e64fed850c0ccf02713"

        getWatchlist(userId, sessionId)

        return binding.root
    }

    private fun getWatchlist(userId: Int, sessionId: String) {
        viewModel.watchlistMovies(userId, sessionId).observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movie ->
                            watchlistAdapter = WatchListAdapter(movie)
                            updateAdapter()
                            watchlistAdapter.notifyDataSetChanged()
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
        binding.rvMylist.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 3)
            adapter = watchlistAdapter
        }
    }
}