package com.junior.delivery.details.domain

import com.junior.delivery.details.data.FoodRepository
import com.junior.delivery.details.data.model.FoodModel
import javax.inject.Inject

class GetFoodsUseCase @Inject constructor(private val repository: FoodRepository) {
    suspend operator fun invoke():List<FoodModel> = repository.getFoods()
}