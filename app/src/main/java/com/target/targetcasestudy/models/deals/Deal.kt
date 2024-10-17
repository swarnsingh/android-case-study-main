package com.target.targetcasestudy.models.deals

import com.target.targetcasestudy.models.Price

data class Deal(
    val id: String,
    val title: String,
    val aisle: String,
    val description: String,
    val salePrice: Price? = null,
    val fulfillment: String,
    val availability: String,
)
