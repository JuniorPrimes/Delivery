package com.junior.delivery.details.domain

import com.junior.delivery.details.data.DishByRestaurantIdRepository
import com.junior.delivery.details.data.model.DishModel
import javax.inject.Inject

class GetDishesByRestaurantIdUseCase @Inject constructor(private val repository: DishByRestaurantIdRepository) {
    suspend operator fun invoke(restaurantId: String):List<DishModel> = repository.getDishes(restaurantId)}