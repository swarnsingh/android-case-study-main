package com.target.targetcasestudy.data.deal.remote

import com.target.targetcasestudy.api.TargetApi
import com.target.targetcasestudy.models.deals.Deal
import com.target.targetcasestudy.models.deals.DealResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DealRemoteDataSourceImpl @Inject constructor(
    private val apiService: TargetApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    DealRemoteDataSource {

    override suspend fun getDeals(): DealResponse = withContext(dispatcher) {
        apiService.retrieveDeals()
    }

    override suspend fun getDealDetail(dealId: String): Deal = withContext(dispatcher) {
        apiService.retrieveDeal(dealId = dealId)
    }
}
