package com.androidexamples.app.interfaces;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by marco on 01/01/2017.
 */

public interface ImageDownloadListener {

    public void onDownloadFinish(List<Bitmap> bitmap);

}
