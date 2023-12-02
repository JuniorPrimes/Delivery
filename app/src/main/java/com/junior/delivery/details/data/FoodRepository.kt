package com.junior.delivery.details.data

import com.junior.delivery.details.data.model.FoodModel
import com.junior.delivery.details.data.model.FoodProvider
import com.junior.delivery.details.data.network.FoodService
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val api: FoodService,
    private val foodProvider: FoodProvider) {
    suspend fun getFoods(): List<FoodModel>{
        val response: List<FoodModel> = api.getFoods()
        foodProvider.foods = response
        return response
    }
}