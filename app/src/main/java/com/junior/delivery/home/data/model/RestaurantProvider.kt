package com.junior.delivery.home.data.model

import javax.inject.Inject

class RestaurantProvider @Inject constructor() {
    var restaurants: List<RestaurantModel> = emptyList()
}