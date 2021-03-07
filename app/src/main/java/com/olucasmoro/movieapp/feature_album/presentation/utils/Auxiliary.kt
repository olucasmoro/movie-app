package com.olucasmoro.movieapp.feature_album.presentation.utils

import android.content.Context
import android.widget.Toast

class Auxiliary {

    companion object {

        fun toastDisplay(context: Context, str: String) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }

    }

}