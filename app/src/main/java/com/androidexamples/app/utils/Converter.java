package com.androidexamples.app.utils;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by marcos on 17/12/2016.
 */

public class Converter {

    /**
     * Convert DIP to Pixels
     * @param context
     * @param dip
     * @return float
     */
    public static float dipToPixels(Context context, float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    /**
     * Convert Pixels to DIP
     * @param context
     * @param pixels
     * @return float
     */
    public static float pixelsToDip(Context context, float pixels) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pixels / density + 0.5f);
    }
}
