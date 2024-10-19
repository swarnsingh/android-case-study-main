package com.target.targetcasestudy.data.deal

import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.data.deal.local.DealLocalDataSource
import com.target.targetcasestudy.data.deal.remote.DealRemoteDataSource
import com.target.targetcasestudy.models.deals.DealResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DealRepositoryTest {

    private val localDataSource: DealLocalDataSource = mockk()
    private val remoteDataSource: DealRemoteDataSource = mockk()
    private lateinit var repository: DealRepository

    @Before
    fun setup() {
        repository = DealRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `getDeals should return deals from remoteDataSource`() = runTest {
        // Given
        val expectedDeals = StaticData.deals
        coEvery { remoteDataSource.getDeals() } returns DealResponse(deals = expectedDeals)

        // When
        val actualDeals = repository.getDeals()

        // Then
        assertEquals(expectedDeals, actualDeals)
    }

    @Test
    fun `getDealDetails should return deal from remoteDataSource`() = runTest {
        // Given
        val dealId = "1"
        val expectedDeal = StaticData.deals[1]
        coEvery { remoteDataSource.getDealDetail(dealId) } returns expectedDeal

        // When
        val actualDeal = repository.getDealDetails(dealId)

        // Then
        assertEquals(expectedDeal, actualDeal)
    }
}
