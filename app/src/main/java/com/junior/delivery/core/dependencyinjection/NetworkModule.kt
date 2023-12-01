package com.junior.delivery.core.dependencyinjection

import com.junior.delivery.home.data.network.RestaurantApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://clutrtqceergafdtqjeb.supabase.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRestaurantApiClient(retrofit: Retrofit):RestaurantApiClient{
        return retrofit.create(RestaurantApiClient::class.java)
    }
}

/*@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideSignInClient(retrofit: Retrofit): SignInClient = retrofit.create(SignInClient::class.java)
}*/