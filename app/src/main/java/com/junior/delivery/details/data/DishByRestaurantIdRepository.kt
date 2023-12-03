package com.junior.delivery.details.data

import com.junior.delivery.details.data.model.DishModel
import com.junior.delivery.details.data.model.DishProvider
import com.junior.delivery.details.data.network.DishByRestaurantIdService
import javax.inject.Inject

class DishByRestaurantIdRepository @Inject constructor(
    private val api: DishByRestaurantIdService,
    private val dishProvider: DishProvider
) {
    suspend fun getDishes(restaurantId: String): List<DishModel>{
        val response: List<DishModel> = api.getDishes(restaurantId)
        dishProvider.dishes = response
        return response
    }
}