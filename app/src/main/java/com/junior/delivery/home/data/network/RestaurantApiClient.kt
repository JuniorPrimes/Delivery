package com.junior.delivery.home.data.network

import com.junior.delivery.home.data.model.RestaurantModel
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantApiClient {
    @GET("rest/v1/restaurant?select=*")
    suspend fun getRestaurants(): Response<List<RestaurantModel>>
}