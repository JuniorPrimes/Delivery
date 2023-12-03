package com.junior.delivery.details.domain

import com.junior.delivery.details.data.FoodByIdRepository
import com.junior.delivery.details.data.model.FoodModel
import javax.inject.Inject

class GetFoodsByIdUseCase @Inject constructor(private val repository: FoodByIdRepository) {
    suspend operator fun invoke(idFoods:List<String>):List<FoodModel> = repository.getFoods(idFoods)
}