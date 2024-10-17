package com.target.targetcasestudy.api

import com.target.targetcasestudy.di.NetworkModule.BASE_URL
import com.target.targetcasestudy.models.deals.Deal
import com.target.targetcasestudy.models.deals.DealResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TargetApi {

  @GET("${BASE_URL}deals")
  suspend fun retrieveDeals(): DealResponse

  @GET("${BASE_URL}deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: String): Deal
}
