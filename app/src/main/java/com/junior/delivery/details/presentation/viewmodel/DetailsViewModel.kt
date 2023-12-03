package com.junior.delivery.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.delivery.details.data.model.DishModel
import com.junior.delivery.details.data.model.FoodModel
import com.junior.delivery.details.domain.GetDishesByRestaurantIdUseCase
import com.junior.delivery.details.domain.GetFoodsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel@Inject constructor(
    var getDishesByRestaurantIdUseCase: GetDishesByRestaurantIdUseCase,
    var getFoodsUseCase: GetFoodsByIdUseCase
):ViewModel() {
    private var restaurantId: String = ""

    fun setRestaurantId(id: String) {
        restaurantId = id
    }

    private val _dishesByRestaurantId = MutableLiveData<List<DishModel>>()
    val dishesByRestaurantId: LiveData<List<DishModel>> = _dishesByRestaurantId

    private val _idFoods = MutableLiveData<List<String>>()
    val idFoods: LiveData<List<String>> = _idFoods

    private val _foods = MutableLiveData<List<FoodModel>>()
    val foods: LiveData<List<FoodModel>> = _foods
    fun loadDishesByRestaurantId() {
        viewModelScope.launch {
            try {
                val result: List<DishModel> = getDishesByRestaurantIdUseCase.invoke(restaurantId)

                _dishesByRestaurantId.postValue(result.take(4))

                val idFoods: List<String> = result.map { it.idFood }
                _idFoods.postValue(idFoods)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    fun loadFoodsById() {
        viewModelScope.launch {
            try {
                val result: List<FoodModel> = getFoodsUseCase.invoke(_idFoods.value?: emptyList())

                _foods.postValue(result.take(4))

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}