package com.target.targetcasestudy.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.R
import com.target.targetcasestudy.compose.theme.TargetAppTheme
import com.target.targetcasestudy.compose.theme.TargetColor
import kotlin.let

@Composable
fun TargetErrorView(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    icon: ImageVector? = Icons.Default.ErrorOutline,
    title: String = stringResource(id = R.string.error_general_title),
    description: String = stringResource(id = R.string.error_general_description),
    primaryButtonLabel: String = stringResource(id = R.string.retry),
    primaryButtonOnClick: (() -> Unit)? = null,
    horizontalAlignment: Alignment.Horizontal = Alignment.Companion.CenterHorizontally,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(480.dp)
                .padding(top = paddingValues.calculateTopPadding()),
            horizontalAlignment = horizontalAlignment,
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    modifier = Modifier.size(32.dp),
                    contentDescription = "",
                    tint = TargetColor.Primary,
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                text = title,
                color = TargetColor.Primary,
                textAlign = getTextAlignment(horizontalAlignment),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    letterSpacing = 0.25.sp,
                    lineHeight = 24.sp,
                ),
            )
            description.let {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    text = it,
                    color = TargetColor.DescriptionColor,
                    textAlign = getTextAlignment(horizontalAlignment),
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        letterSpacing = 0.12.sp,
                        lineHeight = 21.sp,
                    ),
                )
            }
            if (primaryButtonOnClick != null) {
                RetryButton(
                    onClick = { primaryButtonOnClick.invoke() },
                    text = primaryButtonLabel,
                )
            }
        }
    }
}

@Composable
fun RetryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = TargetColor.Gray)
    ) {
        Text(text = text)
    }
}

private fun getTextAlignment(horizontalAlignment: Alignment.Horizontal): TextAlign {
    return when (horizontalAlignment) {
        Alignment.Companion.Start -> TextAlign.Companion.Start
        Alignment.Companion.End -> TextAlign.Companion.End
        else -> TextAlign.Companion.Center
    }
}

@UIModePreviews
@Composable
private fun TargetErrorViewPreview() {
    TargetAppTheme {
        TargetErrorView(
            primaryButtonOnClick = {},
        )
    }
}
