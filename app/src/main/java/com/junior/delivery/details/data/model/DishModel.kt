package com.junior.delivery.details.data.model

import com.google.gson.annotations.SerializedName

data class DishModel(
    @SerializedName("id_restaurant")val idRestaurant: String,
    @SerializedName("id_food")val idFood: String
)