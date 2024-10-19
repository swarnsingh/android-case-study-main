package com.target.targetcasestudy.ui.deal.details

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
class DealDetailsViewModelTest {

    private val localDataSource: DealLocalDataSource = mockk()
    private lateinit var dealRemoteDataSource: DealRemoteDataSource
    private lateinit var dealRepository: DealRepository
    private lateinit var apiService: TargetApi
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: DealDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        apiService = mockk(relaxed = true)
        dealRemoteDataSource = DealRemoteDataSourceImpl(apiService, testDispatcher)
        dealRepository = DealRepository(localDataSource, dealRemoteDataSource)
        viewModel = DealDetailsViewModel(dealRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getDealDetails should update dealStateFlow with Success state`() = runTest {
        // Given
        val expectedDeal = StaticData.deals
        coEvery { dealRepository.getDealDetails(any()) } returns expectedDeal[1]

        // When
        viewModel.getDealDetails("1")

        // Then
        viewModel.dealStateFlow.test {
            val state = awaitItem()
            assertEquals(expectedDeal[1], state.data)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getDealDetails should update dealStateFlow with Error state on exception`() = runTest {
        // Given
        val exception = Throwable("Network error")
        coEvery { dealRepository.getDealDetails(any()) } throws exception

        // When
        viewModel.getDealDetails("1")

        // Then
        viewModel.dealStateFlow.test {
            val state = awaitItem()
            assertTrue(state is ResultState.Error)
            assertEquals(exception.message, (state as ResultState.Error).throwable.message)
            cancelAndIgnoreRemainingEvents()
        }
    }
}