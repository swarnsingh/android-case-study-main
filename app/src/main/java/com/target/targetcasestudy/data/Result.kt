package com.target.targetcasestudy.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transformWhile

sealed class Result<T>(open val data: T?) {
    class Success<T>(override val data: T) : Result<T>(data)
    class Loading<T>(override val data: T? = null) : Result<T>(data)
    class Error<T>(override val data: T? = null, val throwable: Throwable) : Result<T>(data)
}

suspend fun <T> MutableStateFlow<Result<T>>.fetchResponse(
    block: suspend () -> T,
) {
    try {
        value = Result.Loading(value.data)
        value = Result.Success(block())
    } catch (e: Exception) {
        value = Result.Error(value.data, e)
    }
}

suspend fun <T : Result<*>?> Flow<T>.collectUntilSuccessOrError(collector: FlowCollector<T>? = null) {
    transformWhile {
        emit(it)
        it !is Result.Success<*> && it !is Result.Error<*>
    }.collect {
        collector?.emit(it)
    }
}

inline fun <reified T> getResultStatusFlow(crossinline function: suspend () -> T): Flow<Result<T>> {
    return flow {
        emit(Result.Loading())
        val response = function()
        emit(Result.Success(response))
    }.catch { throwable ->
        emit(Result.Error(null, throwable))
    }.flowOn(Dispatchers.IO)
}

suspend fun <T> handleResourceState(
    flow: Flow<Result<T>?>,
    onLoading: ((Result.Loading<T>) -> Unit)? = null,
    onSuccess: ((Result.Success<T>) -> Unit)? = null,
    onError: ((Result.Error<T>) -> Unit)? = null,
) {
    flow.collectUntilSuccessOrError { status ->
        when (status) {
            is Result.Loading -> {
                onLoading?.invoke(status)
            }

            is Result.Success -> {
                onSuccess?.invoke(status)
            }

            is Result.Error -> {
                onError?.invoke(status)
            }

            else -> Unit
        }
    }
}
