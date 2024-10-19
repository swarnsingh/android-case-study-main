package com.target.targetcasestudy.ui.deal.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.compose.UIModePreviews
import com.target.targetcasestudy.compose.theme.TargetAppTheme
import com.target.targetcasestudy.compose.theme.TargetColor
import com.target.targetcasestudy.compose.theme.Typography
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.models.deals.Deal


@Composable
fun DealProductDetailsView(
    modifier: Modifier = Modifier,
    deal: Deal,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Product Details",
                style = Typography.titleLarge.copy(
                    fontWeight = FontWeight.W700,
                    lineHeight = 24.sp,
                )
            )

            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
            )

            Text(
                text = deal.description,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    color = TargetColor.DescriptionColor,
                )
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@UIModePreviews
@Composable
private fun DealProductDetailsViewPreview() {
    TargetAppTheme {
        DealProductDetailsView(
            deal = StaticData.deals[1]
        )
    }
}