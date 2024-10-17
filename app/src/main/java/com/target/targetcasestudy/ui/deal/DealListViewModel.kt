package com.target.targetcasestudy.ui.deal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.data.Result
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

    private val _dealsStateFlow = MutableStateFlow<Result<List<Deal>>>(Result.Loading())
    val dealsStateFlow: StateFlow<Result<List<Deal>>>
        get() = _dealsStateFlow

    fun fetchDeals() {
        viewModelScope.launch {
            _dealsStateFlow.fetchResponse { dealRepository.getDeals() }
        }
    }
}
