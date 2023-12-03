package com.junior.delivery.details.data.model

import com.google.gson.annotations.SerializedName

data class FoodModel(
    val id: Int,
    val name: String,
    @SerializedName("cover_url")val coverURL: String,
    val description: String
)