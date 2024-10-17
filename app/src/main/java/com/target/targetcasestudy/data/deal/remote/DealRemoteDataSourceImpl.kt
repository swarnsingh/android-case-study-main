package com.target.targetcasestudy.data.deal.remote

import com.target.targetcasestudy.api.TargetApi
import com.target.targetcasestudy.models.deals.Deal
import com.target.targetcasestudy.models.deals.DealResponse
import javax.inject.Inject

class DealRemoteDataSourceImpl @Inject constructor(private val apiService: TargetApi) :
    DealRemoteDataSource {

    override suspend fun getDeals(): DealResponse = apiService.retrieveDeals()

    override suspend fun getDealDetail(dealId: String): Deal =
        apiService.retrieveDeal(dealId = dealId)
}
