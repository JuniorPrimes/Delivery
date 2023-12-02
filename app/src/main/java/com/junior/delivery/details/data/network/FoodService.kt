package com.junior.delivery.details.data.network

import com.junior.delivery.details.data.model.FoodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodService @Inject constructor(private val api: FoodClient) {
    suspend fun getFoods():List<FoodModel>{
        return withContext(Dispatchers.IO){
            val response = api.getFoods()
            response.body() ?: emptyList()
        }
    }
}