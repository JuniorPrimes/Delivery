package com.junior.delivery.home.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantModel(
    val name: String,
    //val description: String,
    @SerializedName("cover_url")val coverUrl: String
)
