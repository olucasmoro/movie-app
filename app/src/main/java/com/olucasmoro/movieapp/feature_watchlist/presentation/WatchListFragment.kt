package com.olucasmoro.movieapp.feature_watchlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Toast
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.databinding.FragmentWatchlistBinding
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchListFragment : Fragment() {

    private lateinit var mSharedPreferences: SecurityPreferences

    private val viewModel: WatchListViewModel by viewModel()

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var watchlistAdapter: WatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistBinding.inflate(layoutInflater)
        mSharedPreferences = SecurityPreferences(requireContext())

        val userId = mSharedPreferences.get(Constants.AUTHENTICATION.USER_ID).toInt()
        val sessionId = mSharedPreferences.get(Constants.AUTHENTICATION.SESSION_ID)

        getWatchlist(userId, sessionId)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getWatchlist(userId: Int, sessionId: String) {
        viewModel.watchlistMovies(userId, sessionId).observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Success -> {
                        response.data?.let { movie ->
                            watchlistAdapter = WatchListAdapter(movie, findNavController())
                            updateAdapter()
                            watchlistAdapter.notifyDataSetChanged()
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
        binding.rvMylist.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 3)
            adapter = watchlistAdapter
        }
    }
}