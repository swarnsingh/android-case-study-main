package com.target.targetcasestudy.models.deals

import com.squareup.moshi.Json
import com.target.targetcasestudy.models.Price

data class Deal(
    val id: String,
    val title: String,
    val aisle: String,
    val description: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "regular_price") val regularPrice: Price,
    @Json(name = "sale_price") val salePrice: Price? = null,
    val fulfillment: String,
    val availability: String,
)
