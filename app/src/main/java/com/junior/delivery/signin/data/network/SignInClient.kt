package com.junior.delivery.signin.data.network

import com.junior.delivery.signin.data.network.response.SignInResponse
import retrofit2.Response
import retrofit2.http.GET

interface SignInClient {
    @GET("/v3/cd51b18b-1c89-46df-9425-54651f2f364e")
    suspend fun doSignIn(/*user:String, password:String*/):Response<SignInResponse>
}

