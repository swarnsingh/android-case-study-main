package com.target.targetcasestudy.data.deal.remote

import com.target.targetcasestudy.models.deals.Deal
import com.target.targetcasestudy.models.deals.DealResponse

interface DealRemoteDataSource {
    suspend fun getDeals() : DealResponse
    suspend fun getDealDetail(id: String) : Deal
}