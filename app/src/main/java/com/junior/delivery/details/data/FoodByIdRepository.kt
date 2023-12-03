package com.junior.delivery.details.data

import com.junior.delivery.details.data.model.FoodModel
import com.junior.delivery.details.data.model.FoodProvider
import com.junior.delivery.details.data.network.FoodByIdService
import javax.inject.Inject

class FoodByIdRepository @Inject constructor(
    private val api: FoodByIdService,
    private val foodProvider: FoodProvider) {
    suspend fun getFoods(idFoods:List<String>): List<FoodModel>{
        val response: List<FoodModel> = api.getFoods(idFoods)
        foodProvider.foods = response
        return response
    }
}