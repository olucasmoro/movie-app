package com.olucasmoro.movieapp.feature_user.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olucasmoro.movieapp.MainActivity
import com.olucasmoro.movieapp.databinding.FragmentCheckValidationBinding
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Toast
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel


class CheckValidationFragment : Fragment(), View.OnClickListener {

    private lateinit var mSharedPreferences: SecurityPreferences

    private val viewModel: CheckValidationViewModel by viewModel()
    private val binding by lazy {
        FragmentCheckValidationBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.btnClick.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnClick -> {
                val token = mSharedPreferences.get(Constants.AUTHENTICATION.TOKEN)
                createSession(token)
            }
        }
    }

    private fun createSession(requestToken: String) {
        viewModel.createSession(requestToken).observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Error -> {
                        Toast.toastDisplay(
                            requireContext(),
                            Constants.MESSAGE.FAILURE_CONNECTION
                        )
                    }
                    is CallResults.Success -> {
                        response.data?.let { data ->
                            val mSharedPreferences = SecurityPreferences(requireContext())
                            mSharedPreferences.store(
                                Constants.AUTHENTICATION.SESSION_ID, data.session_id
                            )
                            saveUser(data.session_id)
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            true
                        }
                    }
                }
            }
        }
    }

    private fun saveUser(sessionId: String) {
        val username = mSharedPreferences.get(Constants.AUTHENTICATION.USERNAME)
        val name = mSharedPreferences.get(Constants.AUTHENTICATION.NAME)
        val email = mSharedPreferences.get(Constants.AUTHENTICATION.EMAIL)
        val password = mSharedPreferences.get(Constants.AUTHENTICATION.PASSWORD)

        viewModel.saveUserFirebase(
            username = username, name = name, email = email, password = password,
            session_id = sessionId
        )
    }

}