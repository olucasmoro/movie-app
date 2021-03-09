package com.olucasmoro.movieapp.feature_user.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olucasmoro.movieapp.MainActivity
import com.olucasmoro.movieapp.R
import com.olucasmoro.movieapp.databinding.FragmentCheckValidationBinding
import com.olucasmoro.movieapp.databinding.FragmentUserSignUpBinding
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Auxiliary
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import com.olucasmoro.movieapp.feature_user.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CheckValidationFragment : Fragment(), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModel()
    private val binding by lazy {
        FragmentCheckValidationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding.btnClick.setOnClickListener(this)

        return binding.root
    }

    private fun createSession(requestToken: String) {

        viewModel.createSession(requestToken).observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Error -> {
                        Auxiliary.toastDisplay(
                            requireContext(),
                            Constants.MESSAGE.FAILURE_CONNECTION
                        )
                        false
                    }
                    is CallResults.Success -> {
                        response.data?.let { data ->
                            val mSharedPreferences = SecurityPreferences(requireContext())
                            val s = ""
                            mSharedPreferences.store(
                                Constants.AUTHENTICATION.SESSION_ID,
                                data.session_id
                            )
                            val t = ""
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            true
                        } ?: false
                    }
                }
            } ?: false
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnClick -> {
                val mSharedPreferences = SecurityPreferences(requireContext())
                val token = mSharedPreferences.get(Constants.AUTHENTICATION.TOKEN)
                createSession(token)
            }
        }
    }

}