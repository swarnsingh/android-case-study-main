package com.target.targetcasestudy.ui.deal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.target.targetcasestudy.compose.CircularProgressIndicator
import com.target.targetcasestudy.compose.Separator
import com.target.targetcasestudy.compose.TargetErrorView
import com.target.targetcasestudy.compose.TargetScreen
import com.target.targetcasestudy.compose.UIModePreviews
import com.target.targetcasestudy.compose.appbar.TargetAppBar
import com.target.targetcasestudy.data.ResultState
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.models.deals.Deal

@Composable
fun DealListScreen(
    response: ResultState<List<Deal>>,
    onRetryClick: () -> Unit,
    onDealClick: (Deal) -> Unit,
) {
    TargetScreen(
        topBar = {
            TargetAppBar(title = "List")
        },
    ) { paddingValues ->
        when (response) {

            is ResultState.Loading -> {
                CircularProgressIndicator()
            }

            is ResultState.Success -> {
                DealListContent(
                    modifier = Modifier.padding(paddingValues),
                    deals = response.data,
                    onDealClick
                )
            }

            is ResultState.Error -> {
                TargetErrorView(
                    primaryButtonOnClick = {
                        onRetryClick.invoke()
                    })
            }
        }
    }
}

@Composable
private fun DealListContent(
    modifier: Modifier,
    deals: List<Deal>,
    onDealClick: (Deal) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
    ) {
        items(items = deals, key = { deal -> deal.id }) { deal ->
            DealCardView(
                deal = deal,
                onDealClicked = onDealClick
            )
            Separator(
                modifier = Modifier.padding(
                    start = 16.dp,
                )
            )
        }
    }
}

@UIModePreviews
@Composable
private fun DealListScreenPreview() {
    DealListScreen(
        response = ResultState.Success(StaticData.deals),
        onDealClick = {},
        onRetryClick = {},
    )
}