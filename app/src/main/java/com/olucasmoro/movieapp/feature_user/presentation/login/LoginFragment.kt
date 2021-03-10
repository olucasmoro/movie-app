package com.olucasmoro.movieapp.feature_user.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.olucasmoro.movieapp.MainActivity
import com.olucasmoro.movieapp.databinding.FragmentUserLoginBinding
import com.olucasmoro.movieapp.feature_album.domain.entity.CallResults
import com.olucasmoro.movieapp.feature_album.presentation.utils.Auxiliary
import com.olucasmoro.movieapp.feature_album.presentation.utils.Constants
import com.olucasmoro.movieapp.feature_album.presentation.utils.ProviderType
import com.olucasmoro.movieapp.feature_user.data.local.SecurityPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var mSharedPreferences: SecurityPreferences

    private val viewModel: LoginViewModel by viewModel()
    private val binding by lazy {
        FragmentUserLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        mSharedPreferences = SecurityPreferences(requireContext())

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        isLoggedIn()

        return binding.root
    }

    override fun onStart() {
        setListeners()
        super.onStart()
    }

    override fun onClick(view: View) {
        when (view) {
//            binding.buttonLoginBtnLogin -> //validateToken("aaaa")
            binding.buttonLoginBtnLogin -> signInUser()
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

    /**
     * Realiza o login do usuário
     */
    private fun signInUser() {

        val email = binding.loginEmail.editText?.text.toString()
        val password = binding.loginPassword.editText?.text.toString()

        if (email != "" && password != "") {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        val user = Firebase.auth.currentUser

                        if (user != null) {
                            val userId = user.uid
                            val username = binding.loginEmail.toString()
                            val password = binding.loginPassword.toString()
                            saveUserData(userId, username, password)
                            createToken()
                        }

                    } else {
                        Auxiliary.toastDisplay(requireContext(), "Unregistered user")
                    }
                }
        }
    }

    /**
     * Navega para MovieActivity passando dados do usuários
     */
    private fun showMovieActivity(email: String, provider: ProviderType) {
        mSharedPreferences.store(Constants.AUTHENTICATION.IS_LOGGED, "true")
        mSharedPreferences.store(Constants.AUTHENTICATION.EMAIL, email)
        mSharedPreferences.store(Constants.AUTHENTICATION.PROVIDER, provider.name)
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    /**
     * Navega para MovieActivity
     */
    private fun logged() {
        val mSharedPreferences = SecurityPreferences(requireContext())
        mSharedPreferences.store(Constants.AUTHENTICATION.IS_LOGGED, "true")
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    /**
     * Verifica se o usuário ja está logado
     */
    private fun isLoggedIn() {
//        val mSharedPreferences = SecurityPreferences(requireContext())
        if (mSharedPreferences.get(Constants.AUTHENTICATION.USER_ID) != "") {
            logged()
        }
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
//                            createSessionWithLogin(requestToken)
                            true
                        } ?: false
                    }
                }
            } ?: false
        }
    }

    private fun validateToken(token: String) {
//        val webIntent: Intent = Uri.parse("https://www.themoviedb.org/authenticate/${token}").let { webpage ->
        val webIntent: Intent = Uri.parse("https://github.com/").let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        startActivity(webIntent)
    }

    private fun createSessionWithLogin(requestToken: String) {

        val username = binding.loginEmail.toString()
        val password = binding.loginPassword.toString()

        viewModel.createSessionWithLogin(username, password, requestToken)
            .observe(viewLifecycleOwner) {
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
                                createSession(requestToken)
                                true
                            } ?: false
                        }
                    }
                } ?: false
            }
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
//                            val mSharedPreferences = SecurityPreferences(requireContext())
                            mSharedPreferences.store(
                                Constants.AUTHENTICATION.SESSION_ID,
                                data.session_id
                            )
                            true
                        } ?: false
                    }
                }
            } ?: false
        }
    }

    private fun saveUserData(
        userId: String,
        username: String,
        password: String,
    ) {
//        val mSharedPreferences = SecurityPreferences(requireContext())
        mSharedPreferences.store(Constants.AUTHENTICATION.USER_ID, userId)
        mSharedPreferences.store(Constants.AUTHENTICATION.USERNAME, username)
        mSharedPreferences.store(Constants.AUTHENTICATION.PASSWORD, password)
    }

}