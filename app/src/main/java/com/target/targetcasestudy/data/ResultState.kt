package com.target.targetcasestudy.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transformWhile

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

suspend fun <T : ResultState<*>?> Flow<T>.collectUntilSuccessOrError(collector: FlowCollector<T>? = null) {
    transformWhile {
        emit(it)
        it !is ResultState.Success<*> && it !is ResultState.Error<*>
    }.collect {
        collector?.emit(it)
    }
}

inline fun <reified T> getResultStatusFlow(crossinline function: suspend () -> T): Flow<ResultState<T>> {
    return flow {
        emit(ResultState.Loading())
        val response = function()
        emit(ResultState.Success(response))
    }.catch { throwable ->
        emit(ResultState.Error(null, throwable))
    }.flowOn(Dispatchers.IO)
}

suspend fun <T> handleResourceState(
    flow: Flow<ResultState<T>?>,
    onLoading: ((ResultState.Loading<T>) -> Unit)? = null,
    onSuccess: ((ResultState.Success<T>) -> Unit)? = null,
    onError: ((ResultState.Error<T>) -> Unit)? = null,
) {
    flow.collectUntilSuccessOrError { status ->
        when (status) {
            is ResultState.Loading -> {
                onLoading?.invoke(status)
            }

            is ResultState.Success -> {
                onSuccess?.invoke(status)
            }

            is ResultState.Error -> {
                onError?.invoke(status)
            }

            else -> Unit
        }
    }
}
