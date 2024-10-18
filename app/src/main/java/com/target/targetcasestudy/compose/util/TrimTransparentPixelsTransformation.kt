package com.target.targetcasestudy.compose.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import androidx.core.graphics.createBitmap
import coil.size.Size
import coil.transform.Transformation
import java.io.Serializable

class TrimTransparentPixelsTransformation(
    private val trimLeft: Boolean = true,
    private val trimRight: Boolean = true,
    private val trimTop: Boolean = true,
    private val trimBottom: Boolean = true
) : Transformation, Serializable {

    override val cacheKey = "${javaClass.name} $trimLeft $trimRight $trimBottom $trimTop"

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        var firstX = 0
        var firstY = 0
        var lastX = input.width
        var lastY = input.height
        val pixels = IntArray(input.width * input.height)
        input.getPixels(pixels, 0, input.width, 0, 0, input.width, input.height)
        if (trimLeft) {
            loop@ for (x in 0 until input.width - 1) {
                for (y in 0 until input.height) {
                    if (isColorVisible(pixels[x + y * input.width])) {
                        firstX = x
                        break@loop
                    }
                }
            }
        }
        if (trimRight) {
            loop@ for (x in input.width - 1 downTo firstX) {
                for (y in input.height - 1 downTo 0) {
                    if (isColorVisible(pixels[x + y * input.width])) {
                        lastX = x
                        break@loop
                    }
                }
            }
        }
        if (trimTop) {
            loop@ for (y in 0 until input.height - 1) {
                for (x in firstX until lastX) {
                    if (isColorVisible(pixels[x + y * input.width])) {
                        firstY = y
                        break@loop
                    }
                }
            }
        }
        if (trimBottom) {
            loop@ for (y in input.height - 1 downTo firstY) {
                for (x in lastX downTo firstX) {
                    if (isColorVisible(pixels[x + y * input.width])) {
                        lastY = y
                        break@loop
                    }
                }
            }
        }
        val result =
            createBitmap(width = lastX - firstX, height = lastY - firstY, config = input.config!!)
        val translationMatrix = Matrix().apply {
            setTranslate(0f - firstX, 0f - firstY)
        }
        Canvas(result).drawBitmap(input, translationMatrix, null)
        return result
    }
}


fun isColorVisible(color: Int, alphaThreshold: Int = 5) = Color.alpha(color) > alphaThreshold
