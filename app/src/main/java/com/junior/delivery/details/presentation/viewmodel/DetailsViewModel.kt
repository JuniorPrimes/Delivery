package com.junior.delivery.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.delivery.details.data.model.FoodModel
import com.junior.delivery.details.domain.GetFoodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel@Inject constructor(
    var getFoodsUseCase: GetFoodsUseCase
):ViewModel() {
    private val _foods = MutableLiveData<List<FoodModel>>()
    val foods: LiveData<List<FoodModel>> = _foods

    init {
        loadFoods()
    }
    private fun loadFoods() {
        viewModelScope.launch {
            try {
                val result: List<FoodModel> = getFoodsUseCase()

                _foods.postValue(result.take(4))

            } catch (e: IOException) {
                e.printStackTrace()
            }}
    }
}