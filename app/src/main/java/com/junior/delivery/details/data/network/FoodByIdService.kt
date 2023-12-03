package com.junior.delivery.details.data.network

import com.junior.delivery.details.data.model.FoodModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodByIdService @Inject constructor(private val api: FoodByIdClient) {
    suspend fun getFoods(idFoods:List<String>):List<FoodModel>{
        return withContext(Dispatchers.IO){
            val response = api.getFoods("in.(${idFoods.joinToString(",")})")
            response.body() ?: emptyList()
        }
    }
}