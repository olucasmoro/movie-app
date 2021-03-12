package com.olucasmoro.movieapp.feature_album.presentation.movielist

import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.feature_album.data.model.Movie

class MovieListAdapter2(
    private val listMovie: List<Movie>,
    private val findNavController: NavController
) : RecyclerView.Adapter<MovieListViewHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder2 {
        return MovieListViewHolder2.inflate(parent)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder2, position: Int) {
        holder.bind(listMovie[position], findNavController)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}