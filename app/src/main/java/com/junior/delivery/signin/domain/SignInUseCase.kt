package com.junior.delivery.signin.domain

import com.junior.delivery.core.security.PasswordHash
import com.junior.delivery.signin.data.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val networkRepository: SignInRepository,
    private val cryptoHash: PasswordHash
) {

    suspend operator fun invoke(user: String, password: String):Boolean =
        networkRepository.doSignIn(user, password)
}