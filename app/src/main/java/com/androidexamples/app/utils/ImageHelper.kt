package com.androidexamples.app.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.util.Base64
import android.widget.ImageView

import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL

/**
 * Created by marcos on 17/01/2017.
 */

object ImageHelper {

    // Extrai a imagem do ImageView retornando um Bitmap
    fun extractBitmapFromImageView(imageView: ImageView): Bitmap {
        return (imageView.drawable as BitmapDrawable).bitmap
    }

    // Converte uma imagem em uma string Base64 para Bitmap
    fun decodeBase64ToBitmap(encodedImage: String): Bitmap {
        val decodedString = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    // Converte um Bitmap para uma imagem em string Base64
    fun encodeBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        // Na compressão, utilizar sempre PNG, JPEG perde a qualidade
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    // Faz o download de uma imagem na web e retorna como Bitmap
    fun downloadBitmap(url: String): Bitmap? {
        var bitmap: Bitmap? = null

        try {
            // Faz o download da imagem
            val inputStream = URL(url).openStream()
            // Converte a InputStream do Java para Bitmap
            bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        } catch (ex: Exception) {
        }

        return bitmap
    }

    // Retorna um bitmap circular
    fun getRoundedBitmapDrawable(resources: Resources, bitmap: Bitmap): RoundedBitmapDrawable {
        val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
        roundedBitmapDrawable.isCircular = true
        roundedBitmapDrawable.setAntiAlias(true)
        return roundedBitmapDrawable
    }

    // Arredonda uma imagem nos cantos definidos
    fun getRoundedCornerBitmap(context: Context, bitmapInput: Bitmap, pixels: Int, width: Int, height: Int, squareTopLeft: Boolean, squareTopRight: Boolean, squareBottomLeft: Boolean, squareBottomRight: Boolean): Bitmap {
        val bitmapOutput = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmapOutput)
        val densityMultiplier = context.resources.displayMetrics.density

        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, width, height)
        val rectF = RectF(rect)

        //make sure that our rounded corner is scaled appropriately
        val roundPx = pixels * densityMultiplier

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)

        //draw rectangles over the corners we want to be square
        if (squareTopLeft) {
            canvas.drawRect(0f, 0f, (width / 2).toFloat(), (height / 2).toFloat(), paint)
        }
        if (squareTopRight) {
            canvas.drawRect((width / 2).toFloat(), 0f, width.toFloat(), (height / 2).toFloat(), paint)
        }
        if (squareBottomLeft) {
            canvas.drawRect(0f, (height / 2).toFloat(), (width / 2).toFloat(), height.toFloat(), paint)
        }
        if (squareBottomRight) {
            canvas.drawRect((width / 2).toFloat(), (height / 2).toFloat(), width.toFloat(), height.toFloat(), paint)
        }

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmapInput, 0f, 0f, paint)

        return bitmapOutput
    }
}
