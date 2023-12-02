package com.junior.delivery.signin.data.network

import com.junior.delivery.core.security.PasswordHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInService @Inject constructor(
    private val client: SignInClient,
    private val crypto: PasswordHash,
    private val api: SignInClient
) {

    suspend fun doSignIn(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val (hash, salt) = crypto.getCryptoPassword(password)
            val response = client.doSignIn(/*user, hash, salt*/)

            !response.body()?.name.isNullOrEmpty()
        }
    }
}