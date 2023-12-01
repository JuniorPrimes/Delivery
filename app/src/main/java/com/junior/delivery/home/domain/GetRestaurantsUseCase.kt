package com.junior.delivery.home.domain

import com.junior.delivery.home.data.RestaurantRepository
import com.junior.delivery.home.data.model.RestaurantModel
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(private val repository: RestaurantRepository) {
    suspend operator fun invoke():List<RestaurantModel> = repository.getRestaurants()
}