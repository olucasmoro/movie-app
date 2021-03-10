package com.olucasmoro.movieapp.feature_user.presentation.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.olucasmoro.movieapp.MainActivity
import com.olucasmoro.movieapp.databinding.FragmentUserSignUpBinding
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Auxiliary
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_album.presentation.utils.ProviderType
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import com.olucasmoro.movieapp.feature_user.data.local.User
import com.olucasmoro.movieapp.feature_user.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(), View.OnClickListener {

    private var userToken: String = ""

    private lateinit var rootNode: FirebaseDatabase
    private lateinit var referenceFirebase: DatabaseReference

    private val viewModel: LoginViewModel by viewModel()
    private val binding by lazy {
        FragmentUserSignUpBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootNode = FirebaseDatabase.getInstance()
        referenceFirebase = rootNode.getReference("users")

        return binding.root
    }

    override fun onStart() {
        setListeners()
        super.onStart()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnSignUpBtnLogin -> {
                registerUser()
            }
//            binding.btnSignUpBtnLogin -> registerUser()
            binding.tvLogin -> findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
        }
    }

    private fun registerUser() {
        if (binding.signUpName.editText?.text.toString() != "" &&
            binding.signUpEmail.editText?.text.toString() != "" &&
            binding.signUpPassword.editText?.text.toString() != ""
        ) {
            val email = binding.signUpEmail.editText?.text.toString()
            val password = binding.signUpPassword.editText?.text.toString()
            val username = binding.signUpName.editText?.text.toString()

            val user: User = User(1, username, email, password, "0000")
            referenceFirebase.child(username).setValue(user)

            saveUserData(username, password, email, ProviderType.BASIC)
            createToken()
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToCheckValidationFragment())
            
//            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener {
//
//                    if (it.isSuccessful) {
//                        val user = Firebase.auth.currentUser
//
//                        if (user != null) {
//                            val userId = user.uid
//                            showMovieActivity(username, password, email, ProviderType.BASIC)
//                            createToken()
//                        }
//
////                        showMovieActivity(
////                            username,
////                            password,
////                            it.result?.user?.email ?: "",
////                            ProviderType.BASIC
////                        )
//                    } else {
//                        Auxiliary.toastDisplay(requireContext(), "Error in API")
//                    }
//                }
        } else {
            Auxiliary.toastDisplay(requireContext(), "Fill in all fields")
        }
    }

    private fun setListeners() {
        binding.btnSignUpBtnLogin.setOnClickListener(this)
        binding.tvLogin.setOnClickListener(this)
    }

    private fun saveUserData(
        username: String,
        password: String,
        email: String,
        provider: ProviderType
    ) {
        val mSharedPreferences = SecurityPreferences(requireContext())
        mSharedPreferences.store(Constants.AUTHENTICATION.EMAIL, email)
        mSharedPreferences.store(Constants.AUTHENTICATION.USERNAME, username)
        mSharedPreferences.store(Constants.AUTHENTICATION.PASSWORD, password)
        mSharedPreferences.store(Constants.AUTHENTICATION.PROVIDER, provider.name)
    }

    private fun createToken() {
        viewModel.getToken().observe(viewLifecycleOwner) {
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
                        response.data?.let { user ->
                            val requestToken = user.request_token
                            userToken = requestToken
                            val mSharedPreferences = SecurityPreferences(requireContext())
                            mSharedPreferences.store(Constants.AUTHENTICATION.TOKEN, userToken)
                            validateToken(requestToken)
                            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToCheckValidationFragment())

                            true
                        } ?: false
                    }
                }
            } ?: false
        }
    }

    private fun validateToken(token: String) {
        val webIntent: Intent =
            Uri.parse("https://www.themoviedb.org/authenticate/${token}").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
        userToken = token
        startActivity(webIntent)
    }

    override fun onResume() {
        createSession(userToken)
        super.onResume()
    }

    private fun createSessionWithLogin(requestToken: String) {

//        val mSharedPreferences = SecurityPreferences(requireContext())
//
//        val username = mSharedPreferences.get(Constants.AUTHENTICATION.USERNAME)
//        val password = mSharedPreferences.get(Constants.AUTHENTICATION.PASSWORD)
//
//        viewModel.createSessionWithLogin(requestToken)
//            .observe(viewLifecycleOwner) {
//                it?.let { response ->
//                    when (response) {
//                        is CallResults.Error -> {
//                            Auxiliary.toastDisplay(
//                                requireContext(),
//                                Constants.MESSAGE.FAILURE_CONNECTION
//                            )
//                            false
//                        }
//                        is CallResults.Success -> {
//                            response.data?.let { user ->
//                                val requestToken = user.request_token
//                                createSession(requestToken)
//                                true
//                            } ?: false
//                        }
//                    }
//                } ?: false
//            }
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
                            mSharedPreferences.store(
                                Constants.AUTHENTICATION.SESSION_ID,
                                data.session_id
                            )
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            true
                        } ?: false
                    }
                }
            } ?: false
        }
    }

}