package com.junior.delivery.home.data.network

import com.junior.delivery.home.data.model.RestaurantModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestaurantService @Inject constructor(private val api:RestaurantApiClient) {
    suspend fun getRestaurants():List<RestaurantModel>{
        return withContext(Dispatchers.IO){
            val response = api.getRestaurants()
            response.body() ?: emptyList()
        }
    }
}