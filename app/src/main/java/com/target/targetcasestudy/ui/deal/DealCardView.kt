package com.target.targetcasestudy.ui.deal

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.size.Size
import com.target.targetcasestudy.R
import com.target.targetcasestudy.compose.ImageSource
import com.target.targetcasestudy.compose.UIModePreviews
import com.target.targetcasestudy.compose.painter
import com.target.targetcasestudy.compose.theme.TargetAppTheme
import com.target.targetcasestudy.compose.theme.TargetColor
import com.target.targetcasestudy.compose.theme.Typography
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.models.deals.Deal

@Composable
internal fun DealCardView(
    modifier: Modifier = Modifier,
    deal: Deal,
    onDealClicked: (Deal) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onDealClicked(deal)
            }
            .padding(16.dp),
        color = Color.White,
        shape = RoundedCornerShape(0.dp)
    ) {
        Row {
            Image(
                painter = ImageSource.Url(deal.imageUrl, Size.ORIGINAL).painter(),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
            )

            Spacer(modifier = Modifier.width(16.dp))

            val price = remember(deal) {
                if (deal.salePrice != null) {
                    deal.salePrice.displayString
                } else {
                    deal.regularPrice.displayString
                }
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = price,
                        style = Typography.titleLarge.copy(
                            color = TargetColor.Primary,
                            fontWeight = FontWeight.W700
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(
                            id = R.string.regular_price_format,
                            deal.regularPrice.displayString
                        ),
                        style = Typography.labelMedium.copy(
                            fontWeight = FontWeight.W400
                        )
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = deal.fulfillment,
                    style = Typography.labelMedium.copy(
                        color = TargetColor.Gray,
                        fontWeight = FontWeight.W400
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = deal.title,
                    style = Typography.bodyMedium.copy(
                        fontWeight = FontWeight.W400
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = deal.availability,
                        style = Typography.labelMedium.copy(
                            color = TargetColor.Primary,
                            fontWeight = FontWeight.W400
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.aisle_format, deal.aisle.uppercase()),
                        style = Typography.labelMedium.copy(
                            color = TargetColor.Gray,
                            fontWeight = FontWeight.W400
                        )
                    )
                }
            }
        }
    }
}

@UIModePreviews
@Composable
private fun DealCardViewPreview() {
    TargetAppTheme {
        DealCardView(
            deal = StaticData.deals[0],
            onDealClicked = {}
        )
    }
}
