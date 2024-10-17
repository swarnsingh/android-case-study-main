package com.target.targetcasestudy.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.collectWithLifecycle(
    lifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.CREATED,
    crossinline body: suspend CoroutineScope.(T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            collect { body(it) }
        }
    }
}


inline fun <T> Flow<T>.collectWithLifecycle(
    fragment: Fragment,
    lifecycleState: Lifecycle.State = Lifecycle.State.CREATED,
    crossinline body: suspend CoroutineScope.(T) -> Unit
) {
    collectWithLifecycle(fragment.viewLifecycleOwner, lifecycleState, body)
}

