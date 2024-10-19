package com.target.targetcasestudy.ui.deal

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.target.targetcasestudy.compose.ComposePreviewParameterConfig
import com.target.targetcasestudy.data.ResultState
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.models.deals.Deal

class DealListScreenPreviewProvider :
    PreviewParameterProvider<ComposePreviewParameterConfig<out ResultState<List<Deal>>>> {

    private val loadingState = ComposePreviewParameterConfig {
        ResultState.Loading(data = null)
    }

    private val errorState = ComposePreviewParameterConfig {
        ResultState.Error(data = null, throwable = Throwable("Something went wrong"))
    }

    private val successState = ComposePreviewParameterConfig {
        ResultState.Success(data = StaticData.deals)
    }

    override val values: Sequence<ComposePreviewParameterConfig<out ResultState<List<Deal>>>> =
        sequenceOf(
            successState,
            errorState,
            loadingState,
        ) as Sequence<ComposePreviewParameterConfig<out ResultState<List<Deal>>>>

}
