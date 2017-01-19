package com.androidexamples.app.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by marcos on 17/01/2017.
 */

public class FileUtils {

    /**
     * Read /res/raw folder
     *
     * @param context
     * @param raw
     * @return InputStream
     */
    public static InputStream readRawFile(Context context, int raw) {
        Resources resources = context.getResources();
        InputStream in = resources.openRawResource(raw);
        return in;
    }

    /**
     * Read assets folder file
     *
     * @param context
     * @param fileName
     * @return InputStream
     * @throws IOException
     */
    public static InputStream readAssetsFile(Context context, String fileName) throws IOException {
        AssetManager assets = context.getAssets();
        return assets.open(fileName);
    }
}