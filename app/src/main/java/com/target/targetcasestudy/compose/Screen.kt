package com.target.targetcasestudy.compose

import android.os.Build
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.target.targetcasestudy.compose.keyboard.KeyboardState
import com.target.targetcasestudy.compose.theme.TargetAppTheme
import com.target.targetcasestudy.compose.util.pxToDp
import kotlinx.coroutines.launch


@Composable
fun TargetScreen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    adjustResizeKeyboardBehavior: Boolean = false,
    content: @Composable (PaddingValues) -> Unit,
) {
    val paddingBottom = remember { mutableStateOf(0.dp) }
    val coroutineScope = rememberCoroutineScope()
    var keyboardModifier: Modifier = Modifier
    if (adjustResizeKeyboardBehavior) {
        when {
            Build.VERSION.SDK_INT >= 30 ->
                keyboardModifier = Modifier
                    .consumeWindowInsets(WindowInsets.navigationBars)
                    .imePadding()

            else -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        KeyboardState.keyboardStateFlow.collect { data ->
                            paddingBottom.value = if (data.visible) data.height.pxToDp() else 0.dp
                        }
                    }
                }
            }
        }
    }
    TargetAppTheme {
        Scaffold(
            modifier = modifier.then(keyboardModifier),
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = snackbarHost,
            content = { paddingValues -> // Pass padding values from Scaffold to content
                content(paddingValues)
            },
            contentWindowInsets = WindowInsets.statusBars.union(WindowInsets.navigationBars),
            containerColor = Color.Companion.White,
        )
    }
}
