package com.junior.delivery.details.data.model

import javax.inject.Inject

class DishProvider @Inject constructor() {
    var dishes: List<DishModel> = emptyList()
}