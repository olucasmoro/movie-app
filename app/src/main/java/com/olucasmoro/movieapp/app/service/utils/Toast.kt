package com.olucasmoro.movieapp.app.service.utils

import android.content.Context
import android.widget.Toast

class Toast {

    companion object {
        fun toastDisplay(context: Context, str: String) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }
    }
}