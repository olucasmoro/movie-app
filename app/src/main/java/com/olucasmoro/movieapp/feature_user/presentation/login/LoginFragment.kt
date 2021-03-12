package com.olucasmoro.movieapp.feature_user.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.olucasmoro.movieapp.app.service.utils.Toast
import com.olucasmoro.movieapp.databinding.FragmentUserLoginBinding
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var mSharedPreferences: SecurityPreferences

    private val viewModel: LoginViewModel by viewModel()
//    private val binding by lazy {
//        FragmentUserLoginBinding.inflate(layoutInflater)
//    }

    private var _binding: FragmentUserLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserLoginBinding.inflate(layoutInflater)

        mSharedPreferences = SecurityPreferences(requireContext())

        setListeners()

        return binding.root
    }

//    override fun onStart() {
//        setListeners()
//        super.onStart()
//    }

    override fun onClick(view: View) {
        when (view) {
            binding.buttonLoginBtnLogin -> verifyUser()
            binding.tvNewUser -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
    }

    /**
     * Inicializa os eventos de click
     */
    private fun setListeners() {
        binding.buttonLoginBtnLogin.setOnClickListener(this)
        binding.tvNewUser.setOnClickListener(this)
        binding.tvForgetPassword.setOnClickListener(this)
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
                    // Salvar usuário no Cache
                }
                Constants.FIREBASE.USER_NOT_FOUND -> {
                    binding.loginUsername.requestFocus()
                }
                Constants.FIREBASE.WRONG_PASSWORD -> {
                    binding.loginPassword.requestFocus()
                }
            }
        }
    }

}