package com.junior.delivery.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.delivery.home.data.model.RestaurantModel
import com.junior.delivery.home.domain.GetRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var getRestaurantsUseCase:GetRestaurantsUseCase
):ViewModel() {
    private val _restaurants = MutableLiveData<List<RestaurantModel>>()
    val restaurants: LiveData<List<RestaurantModel>> = _restaurants

    init {
        loadRestaurants()
    }
    private fun loadRestaurants() {
        viewModelScope.launch {
            val result: List<RestaurantModel> = getRestaurantsUseCase()

            _restaurants.postValue(result.take(4))
        }
    }
}

