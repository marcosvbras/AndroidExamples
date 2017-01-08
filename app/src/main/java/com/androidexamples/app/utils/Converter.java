package com.androidexamples.app.utils;

import android.content.Context;

/**
 * Created by marco on 17/12/2016.
 */

public class Converter {

    public static float toPixels(Context context, float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
