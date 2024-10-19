package com.target.targetcasestudy.ui.deal.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.R
import com.target.targetcasestudy.compose.CircularProgressIndicator
import com.target.targetcasestudy.compose.ComposePreviewParameterConfig
import com.target.targetcasestudy.compose.Separator
import com.target.targetcasestudy.compose.TargetErrorView
import com.target.targetcasestudy.compose.TargetScreen
import com.target.targetcasestudy.compose.UIModePreviews
import com.target.targetcasestudy.compose.appbar.TargetAppBar
import com.target.targetcasestudy.compose.theme.TargetAppTheme
import com.target.targetcasestudy.compose.theme.TargetColor
import com.target.targetcasestudy.compose.theme.TargetShapes
import com.target.targetcasestudy.data.ResultState
import com.target.targetcasestudy.models.deals.Deal

@Composable
fun DealDetailScreen(
    response: ResultState<Deal>,
    onBackClick: () -> Unit,
    onAddToCartClick: (String) -> Unit,
    onRetryClick: () -> Unit,
) {
    TargetScreen(
        topBar = {
            TargetAppBar(
                title = stringResource(id = R.string.details),
                onBackClick = { onBackClick.invoke() },
            )
        },
    ) { paddingValues ->
        when (response) {
            is ResultState.Loading -> {
                CircularProgressIndicator()
            }

            is ResultState.Success -> {
                DealDetailScreenContent(
                    modifier = Modifier.padding(paddingValues),
                    deal = response.data,
                    onAddToCartClick = onAddToCartClick
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
private fun DealDetailScreenContent(
    modifier: Modifier,
    deal: Deal,
    onAddToCartClick: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .background(TargetColor.BackgroundColor)
            .verticalScroll(scrollState)
    ) {
        DealDetailsItemView(deal = deal)
        Separator()
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
                .background(color = TargetColor.SpacerColor)
        )
        Separator()
        DealProductDetailsView(deal = deal)
        AddToCart(
            dealId = deal.id,
            onAddToCartClick = onAddToCartClick
        )
    }
}

@Composable
private fun AddToCart(
    dealId: String,
    onAddToCartClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 1.dp)
            .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White),

        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onAddToCartClick(dealId)
            },
            colors = ButtonDefaults.buttonColors(containerColor = TargetColor.ButtonColor), // Red background
            shape = TargetShapes.extraSmall,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(44.dp)
        ) {
            Text(
                text = stringResource(R.string.add_to_cart),
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
                color = Color.White,
                lineHeight = 24.sp,
            )
        }
    }
}

@UIModePreviews
@Composable
private fun DealDetailScreenPreview(
    @PreviewParameter(DealDetailsScreenPreviewProvider::class) previewConfig: ComposePreviewParameterConfig<ResultState<Deal>>,
) {
    TargetAppTheme {
        DealDetailScreen(
            response = previewConfig.value(),
            onAddToCartClick = {},
            onBackClick = {},
            onRetryClick = {},
        )
    }
}
