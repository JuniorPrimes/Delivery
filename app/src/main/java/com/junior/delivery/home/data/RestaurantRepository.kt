package com.junior.delivery.home.data

import com.junior.delivery.home.data.model.RestaurantModel
import com.junior.delivery.home.data.model.RestaurantProvider
import com.junior.delivery.home.data.network.RestaurantService
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantService,
    private val restaurantProvider: RestaurantProvider) {
    suspend fun getRestaurants(): List<RestaurantModel>{
        val response: List<RestaurantModel> = api.getRestaurants()
        restaurantProvider.restaurants = response
        return response
    }
}