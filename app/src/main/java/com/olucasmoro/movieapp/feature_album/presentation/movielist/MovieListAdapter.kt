package com.olucasmoro.movieapp.feature_album.presentation.movielist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.feature_album.data.model.Movie

class MovieListAdapter(
    private val listMovie: List<Movie>,
) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}