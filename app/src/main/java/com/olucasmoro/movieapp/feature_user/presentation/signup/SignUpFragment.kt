package com.olucasmoro.movieapp.feature_user.presentation.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.olucasmoro.movieapp.databinding.FragmentUserSignUpBinding
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Toast
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(), View.OnClickListener {

    private lateinit var mSharedPreferences: SecurityPreferences

    private val viewModel: SignUpViewModel by viewModel()

    private var _binding: FragmentUserSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserSignUpBinding.inflate(layoutInflater)

        setListeners()

        mSharedPreferences = SecurityPreferences(requireContext())

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnSignUpBtnLogin -> registerUser()
            binding.tvLogin -> findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }
    }

    private fun registerUser() {
        if (binding.signUpUsername.editText?.text.toString() != "" &&
            binding.signUpName.editText?.text.toString() != "" &&
            binding.signUpEmail.editText?.text.toString() != "" &&
            binding.signUpPassword.editText?.text.toString() != ""
        ) {
            val email = binding.signUpEmail.editText?.text.toString()
            val password = binding.signUpPassword.editText?.text.toString()
            val username = binding.signUpUsername.editText?.text.toString()
            val name = binding.signUpName.editText?.text.toString()

            mSharedPreferences.store(Constants.AUTHENTICATION.EMAIL, email)
            mSharedPreferences.store(Constants.AUTHENTICATION.USERNAME, username)
            mSharedPreferences.store(Constants.AUTHENTICATION.NAME, name)
            mSharedPreferences.store(Constants.AUTHENTICATION.PASSWORD, password)

            createToken()
        } else {
            Toast.toastDisplay(requireContext(), "Fill in all fields")
        }
    }

    private fun setListeners() {
        binding.btnSignUpBtnLogin.setOnClickListener(this)
        binding.tvLogin.setOnClickListener(this)
    }

    private fun createToken() {
        viewModel.getToken().observe(viewLifecycleOwner) {
            it?.let { response ->
                when (response) {
                    is CallResults.Error -> {
                        Toast.toastDisplay(
                            requireContext(),
                            Constants.MESSAGE.FAILURE_CONNECTION
                        )
                    }
                    is CallResults.Success -> {
                        response.data?.let { user ->
                            mSharedPreferences.store(
                                Constants.AUTHENTICATION.TOKEN,
                                user.request_token
                            )
                            validateToken(user.request_token)
                            true
                        }
                    }
                }
            }
        }
    }

    private fun validateToken(token: String) {
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(Constants.API.BASE_URL_AUTHENTICATION + token)
        )
        startActivity(webIntent)
        findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToCheckValidationFragment())
    }
}