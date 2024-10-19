package com.target.targetcasestudy.data

import kotlinx.coroutines.flow.MutableStateFlow

sealed class ResultState<T>(open val data: T?) {
    class Success<T>(override val data: T) : ResultState<T>(data)
    class Loading<T>(override val data: T? = null) : ResultState<T>(data)
    class Error<T>(override val data: T? = null, val throwable: Throwable) : ResultState<T>(data)
}

suspend fun <T> MutableStateFlow<ResultState<T>>.fetchResponse(
    block: suspend () -> T,
) {
    try {
        value = ResultState.Loading(value.data)
        value = ResultState.Success(block())
    } catch (e: Exception) {
        value = ResultState.Error(value.data, e)
    }
}
