package com.target.targetcasestudy.ui.deal.details

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.size.Size
import com.target.targetcasestudy.R
import com.target.targetcasestudy.compose.util.ImageSource
import com.target.targetcasestudy.compose.UIModePreviews
import com.target.targetcasestudy.compose.util.painter
import com.target.targetcasestudy.compose.theme.TargetAppTheme
import com.target.targetcasestudy.compose.theme.TargetColor
import com.target.targetcasestudy.compose.theme.Typography
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.models.deals.Deal

@Composable
fun DealDetailsItemView(
    modifier: Modifier = Modifier,
    deal: Deal,
) {
    val configuration = LocalConfiguration.current

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Image(
                painter = ImageSource.Url(deal.imageUrl, Size.ORIGINAL).painter(),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            Modifier.height(450.dp)
                        } else {
                            Modifier.height(343.dp)
                        }
                    )
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
            )

            Text(
                text = deal.title,
                style = Typography.bodyMedium.copy(
                    fontWeight = FontWeight.W400,
                    fontSize = 18.sp,
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
            )

            val price = remember(deal) {
                if (deal.salePrice != null) {
                    deal.salePrice.displayString
                } else {
                    deal.regularPrice.displayString
                }
            }

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

            Text(
                text = deal.fulfillment,
                style = Typography.labelMedium.copy(
                    color = TargetColor.Gray,
                    fontWeight = FontWeight.W400,
                    lineHeight = 20.sp,
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
private fun DealDetailsItemViewPreview() {
    TargetAppTheme {
        DealDetailsItemView(
            deal = StaticData.deals[1]
        )
    }
}