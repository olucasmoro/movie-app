package com.olucasmoro.movieapp.feature_user.data.source

import androidx.lifecycle.liveData
import com.google.firebase.database.*
import com.olucasmoro.movieapp.app.service.model.CallResults
import com.olucasmoro.movieapp.app.service.utils.Constants
import com.olucasmoro.movieapp.feature_user.data.api.UserApiService
import com.olucasmoro.movieapp.feature_user.data.local.User
import java.net.ConnectException

class UserRemoteDataImpl(private val apiService: UserApiService) : UserRemoteData {

    override fun getToken(apiKey: String) = liveData {
        try {
            val response = apiService.createToken(apiKey)
            if (response.isSuccessful) {
                emit(CallResults.Success(data = response.body()))
            } else {
                emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE)))
            }
        } catch (e: ConnectException) {
            emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE_CONNECTION)))
        } catch (e: Exception) {
            emit(CallResults.Error(exception = e))
        }
    }

    override fun createSession(
        apiKey: String,
        requestToken: String
    ) = liveData {
        try {
            val response =
                apiService.createSession(apiKey, requestToken)
            if (response.isSuccessful) {
                emit(CallResults.Success(data = response.body()))
            } else {
                emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE)))
            }
        } catch (e: ConnectException) {
            emit(CallResults.Error(exception = Exception(Constants.MESSAGE.FAILURE_CONNECTION)))
        } catch (e: Exception) {
            emit(CallResults.Error(exception = e))
        }
    }

    override fun saveUserFirebase(
        username: String,
        name: String,
        email: String,
        password: String,
        session_id: String
    ) {
        val user = User(username, name, email, password, session_id)
        val rootNode: FirebaseDatabase = FirebaseDatabase.getInstance()
        val referenceFirebase: DatabaseReference = rootNode.getReference("users")
        referenceFirebase.child(username).setValue(user)
    }

    override fun checkUserFirebase(username: String, password: String): Int {
        val reference =
            FirebaseDatabase.getInstance().getReference(Constants.FIREBASE.NAME_DATABASE)
        val checkUser = reference.orderByChild(Constants.AUTHENTICATION.USERNAME).equalTo(username)

        var response: Int = Constants.FIREBASE.ERROR

        checkUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                response = if (dataSnapshot.exists()) {
                    val passwordFromDB =
                        dataSnapshot.child(username).child(Constants.AUTHENTICATION.PASSWORD)
                            .getValue(
                                String::class.java
                            )
                    if (passwordFromDB == password) {
                        Constants.FIREBASE.SUCCESS
                    } else {
                        Constants.FIREBASE.WRONG_PASSWORD
                    }
                } else {
                    Constants.FIREBASE.USER_NOT_FOUND
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        return response
    }
}