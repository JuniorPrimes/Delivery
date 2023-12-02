package com.junior.delivery.signin.data

import com.junior.delivery.signin.data.network.SignInService
import javax.inject.Inject

class SignInRepository @Inject constructor(private val api: SignInService) {
    suspend fun doSignIn(user: String, password: String): Boolean = api.doSignIn(user, password)
}