package com.target.targetcasestudy.data.deal.remote

import com.target.targetcasestudy.api.TargetApi
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.models.deals.DealResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DealRemoteDataSourceImplTest {

    private val apiService: TargetApi = mockk()
    private lateinit var dataSource: DealRemoteDataSourceImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dataSource = DealRemoteDataSourceImpl(apiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getDeals should return DealResponse from apiService`() = runTest {
        // Given
        val expectedDealResponse = DealResponse(deals = StaticData.deals)
        coEvery { apiService.retrieveDeals() } returns expectedDealResponse

        // When
        val actualDealResponse = dataSource.getDeals()

        // Then
        assertEquals(expectedDealResponse, actualDealResponse)
    }

    @Test
    fun `getDealDetail should return Deal from apiService`() = runTest {
        // Given
        val dealId = "1"
        val expectedDeal = StaticData.deals[1]
        coEvery { apiService.retrieveDeal(dealId) } returns expectedDeal

        // When
        val actualDeal = dataSource.getDealDetail(dealId)

        // Then
        assertEquals(expectedDeal, actualDeal)
    }
}