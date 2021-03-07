package com.olucasmoro.movieapp.feature_album.presentation.moviesearch

import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.olucasmoro.movieapp.feature_album.data.model.Search

class MovieSearchAdapter(
    private val listMovie: List<Search>,
    private val findNavController: NavController
) : RecyclerView.Adapter<MovieSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        return MovieSearchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.bind(listMovie[position], findNavController)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}