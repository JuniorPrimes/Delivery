package com.junior.delivery.details.data.network

import com.junior.delivery.details.data.model.DishModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DishByRestaurantIdService @Inject constructor(private val api: DishByRestaurantIdClient) {
    suspend fun getDishes(restaurantId: String):List<DishModel>{
        return withContext(Dispatchers.IO){
            val response = api.getDishes("eq.$restaurantId")
            response.body() ?: emptyList()
        }
    }
}