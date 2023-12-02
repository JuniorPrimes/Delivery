package com.junior.delivery.core.dependencyinjection

import com.junior.delivery.details.data.network.FoodClient
import com.junior.delivery.home.data.network.RestaurantClient
import com.junior.delivery.signin.data.network.SignInClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient.Builder
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        val httpClient = Builder()
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNsdXRydHFjZWVyZ2FmZHRxamViIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDAwNTI2MDksImV4cCI6MjAxNTYyODYwOX0.M0LOr8aHu4GA8TE9B-7skWZl348m2YF1ACUX_CZQcBw")
                .header("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNsdXRydHFjZWVyZ2FmZHRxamViIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDAwNTI2MDksImV4cCI6MjAxNTYyODYwOX0.M0LOr8aHu4GA8TE9B-7skWZl348m2YF1ACUX_CZQcBw")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })

        return Retrofit.Builder()
            .baseUrl("https://clutrtqceergafdtqjeb.supabase.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideSignInClient(retrofit: Retrofit): SignInClient = retrofit.create(SignInClient::class.java)

    @Singleton
    @Provides
    fun provideRestaurantClient(retrofit: Retrofit): RestaurantClient {
        return retrofit.create(RestaurantClient::class.java)
    }

    @Singleton
    @Provides
    fun provideFoodClient(retrofit: Retrofit): FoodClient {
        return retrofit.create(FoodClient::class.java)
    }
}