package com.target.targetcasestudy.ui.deal.details

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
class DealDetailsViewModel @Inject constructor(
    private val dealRepository: DealRepository
) : ViewModel() {

    private val _dealStateFlow = MutableStateFlow<ResultState<Deal>>(ResultState.Loading())
    val dealStateFlow: StateFlow<ResultState<Deal>>
        get() = _dealStateFlow

    fun getDealDetails(id: String) {
        viewModelScope.launch {
            _dealStateFlow.fetchResponse { dealRepository.getDealDetails(id) }
        }
    }
}
