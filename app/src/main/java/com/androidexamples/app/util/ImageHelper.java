package com.androidexamples.app.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageHelper {

    // Faz o download de uma imagem na web e retorna como Bitmap
    public static Bitmap downloadBitmap(String url) {
        Bitmap bitmap = null;

        try {
            // Faz o download da imagem
            InputStream inputStream = new URL(url).openStream();
            // Converte a InputStream do Java para Bitmap
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch(Exception ex) {}

        return bitmap;
    }

    // Arredonda uma imagem nos cantos definidos
    public static Bitmap getRoundedCornerBitmap(Context context, Bitmap bitmapInput, int pixels, int width, int height, boolean squareTopLeft, boolean squareTopRight, boolean squareBottomLeft, boolean squareBottomRight) {
        Bitmap bitmapOutput = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapOutput);
        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        //make sure that our rounded corner is scaled appropriately
        final float roundPx = pixels*densityMultiplier;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        //draw rectangles over the corners we want to be square
        if (squareTopLeft ) {
            canvas.drawRect(0, 0, width/2, height/2, paint);
        }
        if (squareTopRight ) {
            canvas.drawRect(width/2, 0, width, height/2, paint);
        }
        if (squareBottomLeft ) {
            canvas.drawRect(0, height/2, width/2, height, paint);
        }
        if (squareBottomRight ) {
            canvas.drawRect(width/2, height/2, width, height, paint);
        }

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapInput, 0,0, paint);

        return bitmapOutput;
    }
}
