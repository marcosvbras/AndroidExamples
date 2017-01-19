package com.androidexamples.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by marcos on 18/01/2017.
 */

public class TesteBitmap {

    private void testarBitmap(Context context, int resourceImageId, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(context.getResources(), resourceImageId, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;

        int scaleFactor = Math.min(outWidth / width, outHeight / height);

        /* If set to a value > 1, requests the decoder to subsample the original image, returning a smaller image to save memory.
        * The sample size is the number of pixels in either dimension that correspond to a single pixel in the decoded bitmap.
        * For example, inSampleSize == 4 returns an image that is 1/4 the width/height of the original, and 1/16 the number of pixels.
        * Any value <= 1 is treated the same as 1. */
        options.inSampleSize = scaleFactor;

        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceImageId, options);
    }

    /*
    * ARGB_8888
    * Each pixel is stored on 4 bytes. Each channel (RGB and alpha for translucency) is stored with 8 bits of precision
    * (256 possible values.) This configuration is very flexible and offers the best quality. It should be used whenever possible.
    * */
}
