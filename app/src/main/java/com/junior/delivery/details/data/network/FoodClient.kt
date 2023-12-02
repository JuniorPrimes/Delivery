package com.junior.delivery.details.data.network

import com.junior.delivery.details.data.model.FoodModel
import retrofit2.Response
import retrofit2.http.GET

interface FoodClient {
    @GET("rest/v1/food?select=*")
    suspend fun getFoods(): Response<List<FoodModel>>
}