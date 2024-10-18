package com.target.targetcasestudy.ui.deal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.data.ResultState
import com.target.targetcasestudy.data.deal.DealRepository
import com.target.targetcasestudy.data.fetchResponse
import com.target.targetcasestudy.models.deals.Deal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealListViewModel @Inject constructor(
    private val dealRepository: DealRepository
) : ViewModel() {

    private val _dealsStateFlow = MutableStateFlow<ResultState<List<Deal>>>(ResultState.Loading())
    val dealsStateFlow: StateFlow<ResultState<List<Deal>>>
        get() = _dealsStateFlow

    init {
        fetchDeals()
    }

    fun fetchDeals() {
        viewModelScope.launch {
            _dealsStateFlow.fetchResponse { dealRepository.getDeals() }
        }
    }
}
