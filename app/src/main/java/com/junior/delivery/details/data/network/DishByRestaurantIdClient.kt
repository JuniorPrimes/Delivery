package com.junior.delivery.details.data.network

import com.junior.delivery.details.data.model.DishModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DishByRestaurantIdClient {
    @GET("rest/v1/dish")
    suspend fun getDishes(@Query("id_restaurant") idRestaurant: String): Response<List<DishModel>>
}