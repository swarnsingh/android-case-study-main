package com.target.targetcasestudy.compose

import androidx.compose.runtime.Composable

fun interface ComposePreviewParameterConfig<T> {
    @Composable
    fun value(): T
}
