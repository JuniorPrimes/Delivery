package com.junior.delivery.details.data.model

import javax.inject.Inject

class FoodProvider @Inject constructor() {
    var foods: List<FoodModel> = emptyList()
}