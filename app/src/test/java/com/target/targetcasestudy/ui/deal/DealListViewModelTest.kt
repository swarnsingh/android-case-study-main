package com.target.targetcasestudy.ui.deal

import app.cash.turbine.test
import com.target.targetcasestudy.api.TargetApi
import com.target.targetcasestudy.data.ResultState
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.data.deal.DealRepository
import com.target.targetcasestudy.data.deal.local.DealLocalDataSource
import com.target.targetcasestudy.data.deal.remote.DealRemoteDataSource
import com.target.targetcasestudy.data.deal.remote.DealRemoteDataSourceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DealListViewModelTest {

    private val localDataSource: DealLocalDataSource = mockk()
    private lateinit var dealRemoteDataSource: DealRemoteDataSource
    private lateinit var dealRepository: DealRepository
    private lateinit var viewModel: DealListViewModel
    private lateinit var apiService: TargetApi
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        apiService = mockk(relaxed = true)
        dealRemoteDataSource = DealRemoteDataSourceImpl(apiService, testDispatcher)
        dealRepository = DealRepository(localDataSource, dealRemoteDataSource)
        viewModel = DealListViewModel(dealRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchDeals should update dealsStateFlow with Success state`() = runTest {
        // Given
        val expectedDeals = StaticData.deals
        coEvery { dealRepository.getDeals() } returns expectedDeals

        // When
        viewModel.fetchDeals()

        // Then
        viewModel.dealsStateFlow.test {
            val state = awaitItem()
            assertEquals(expectedDeals, state.data)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchDeals should update dealsStateFlow with Error state on exception`() = runTest {
        // Given
        val exception = Throwable("Network error")
        coEvery { dealRepository.getDeals() } throws exception

        // When
        viewModel.fetchDeals()

        // Then
        viewModel.dealsStateFlow.test {
            val state = awaitItem()
            assertTrue(state is ResultState.Error)
            assertEquals(exception, (state as ResultState.Error).throwable)
            cancelAndIgnoreRemainingEvents()
        }
    }
}