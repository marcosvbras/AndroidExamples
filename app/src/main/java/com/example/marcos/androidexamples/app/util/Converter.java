package com.example.marcos.androidexamples.app.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by marco on 17/12/2016.
 */

public class Converter {

    public static float toPixels(Context context, float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
