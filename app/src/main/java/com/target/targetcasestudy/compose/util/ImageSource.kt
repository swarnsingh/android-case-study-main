package com.target.targetcasestudy.compose.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.drawablepainter.rememberDrawablePainter

sealed class ImageSource {
    data class Vector(val imageVector: ImageVector) : ImageSource()
    data class Resource(val drawableRes: Int) : ImageSource()
    data class Url(val url: String, val size: Size? = null) : ImageSource()
    data class Drawable(val drawable: android.graphics.drawable.Drawable) : ImageSource()

    companion object {
        operator fun invoke(imageVector: ImageVector): Vector = Vector(imageVector)
        operator fun invoke(drawableRes: Int): Resource = Resource(drawableRes)
        operator fun invoke(url: String): Url = Url(url)
        operator fun invoke(drawable: android.graphics.drawable.Drawable): Drawable =
            Drawable(drawable)
    }
}

@Composable
fun ImageSource.painter() = when (this) {
    is ImageSource.Url -> {
        rememberAsyncImagePainter(
            if (size == null) {
                url
            } else {
                ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .size(size)
                    .build()
            },
        )
    }

    is ImageSource.Resource -> painterResource(drawableRes)
    is ImageSource.Vector -> rememberVectorPainter(imageVector)
    is ImageSource.Drawable -> rememberDrawablePainter(drawable)
}
