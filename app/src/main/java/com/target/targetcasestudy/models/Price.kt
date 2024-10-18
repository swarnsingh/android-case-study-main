package com.target.targetcasestudy.models

import com.squareup.moshi.Json

data class Price(
    @Json(name = "amount_in_cents") val amountInCents: Int,
    @Json(name = "currency_symbol") val currencySymbol: String,
    @Json(name = "display_string") val displayString: String
)
