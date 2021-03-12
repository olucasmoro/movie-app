package com.olucasmoro.movieapp.feature_watchlist.presentation

import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.feature_watchlist.data.model.Movie

class WatchListAdapter(
    private val listMovie: List<Movie>,
    private val findNavController: NavController
) : RecyclerView.Adapter<WatchListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        return WatchListViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.bind(listMovie[position], findNavController)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}