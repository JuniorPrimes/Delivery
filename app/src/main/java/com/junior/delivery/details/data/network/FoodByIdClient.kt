package com.junior.delivery.details.data.network

import com.junior.delivery.details.data.model.FoodModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodByIdClient {
    @GET("rest/v1/food")
    suspend fun getFoods(@Query("id") idFood: String): Response<List<FoodModel>>
}