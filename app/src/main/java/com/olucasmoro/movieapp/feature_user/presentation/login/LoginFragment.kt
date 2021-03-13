package com.olucasmoro.movieapp.feature_user.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.olucasmoro.movieapp.MainActivity
import com.olucasmoro.movieapp.app.service.utils.Toast
import com.olucasmoro.movieapp.databinding.FragmentUserLoginBinding
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModel()

    private var _binding: FragmentUserLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserLoginBinding.inflate(layoutInflater)

        setListeners()

        return binding.root
    }

    override fun onClick(view: View) {
        when (view) {
            binding.buttonLoginBtnLogin -> verifyUser()
            binding.tvNewUser -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
    }

    private fun setListeners() {
        binding.buttonLoginBtnLogin.setOnClickListener(this)
        binding.tvNewUser.setOnClickListener(this)
    }

    private fun verifyUser() {
        val username = binding.loginUsername.editText?.text.toString()
        val password = binding.loginPassword.editText?.text.toString()

        if (username != "" && password != "") {
            val response = viewModel.checkUserFirebase(username, password)

            when (response) {
                Constants.FIREBASE.ERROR -> {
                    Toast.toastDisplay(requireContext(), "Error!")
                }
                Constants.FIREBASE.SUCCESS -> {
                    val user = viewModel.getUserFirebase(username)
                    viewModel.store(Constants.AUTHENTICATION.NAME, user.name)
                    viewModel.store(Constants.AUTHENTICATION.EMAIL, user.email)
                    viewModel.store(Constants.AUTHENTICATION.PASSWORD, user.password)
                    viewModel.store(Constants.AUTHENTICATION.SESSION_ID, user.sessionId.toString())
                    viewModel.store(Constants.AUTHENTICATION.USERNAME, user.username)

                    startActivity(Intent(requireContext(), MainActivity::class.java))
                }
                Constants.FIREBASE.USER_NOT_FOUND -> {
                    binding.loginUsername.requestFocus()
                }
                Constants.FIREBASE.WRONG_PASSWORD -> {
                    binding.loginPassword.requestFocus()
                }
            }
        } else {
            Toast.toastDisplay(requireContext(), "Empty fields ")
        }
    }

}