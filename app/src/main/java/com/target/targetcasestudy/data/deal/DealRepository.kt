package com.target.targetcasestudy.data.deal

import com.target.targetcasestudy.data.deal.local.DealLocalDataSource
import com.target.targetcasestudy.data.deal.remote.DealRemoteDataSource
import com.target.targetcasestudy.models.deals.Deal
import javax.inject.Inject

class DealRepository @Inject constructor(
    private val localDataSource: DealLocalDataSource,
    private val remoteDataSource: DealRemoteDataSource
) {

    suspend fun getDeals(): List<Deal> {
        return remoteDataSource.getDeals().deals
    }

    suspend fun getDealDetails(dealId: String): Deal {
        return remoteDataSource.getDealDetail(dealId)
    }
}
