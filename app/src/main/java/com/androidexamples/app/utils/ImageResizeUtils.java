package com.androidexamples.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

/**
 * Faz o resize de uma imagem de forma eficiente
 *
 * http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
 *
 */

/**
 * Created by marcos on 17/01/2017.
 */

public class ImageResizeUtils {

    private static final String TAG = ImageResizeUtils.class.getName();

    // Redimensiona um Bitmap de acordo com um tamamho máximo definido
    public static Bitmap resizeBitmap(Bitmap bitmapOriginal, int maxSize) {
        if(bitmapOriginal != null) {
            int bitmapWidth = bitmapOriginal.getWidth();
            int bitmapHeight = bitmapOriginal.getHeight();

            float bitmapRatio = (float)bitmapWidth / (float)bitmapHeight;

            // Definindo se a imagem é horizontal ou vertical
            boolean isLandscapeImage = bitmapRatio > 1 ? true : false;

            if(isLandscapeImage && bitmapWidth > maxSize) {
                bitmapWidth = maxSize;
                bitmapHeight = (int)(bitmapWidth / bitmapRatio);
                bitmapOriginal =  Bitmap.createScaledBitmap(bitmapOriginal, bitmapWidth, bitmapHeight, true);
            } else if(!isLandscapeImage && bitmapHeight > maxSize) {
                bitmapHeight = maxSize;
                bitmapWidth = (int)(bitmapHeight * bitmapRatio);
                bitmapOriginal = Bitmap.createScaledBitmap(bitmapOriginal, bitmapWidth, bitmapHeight, true);
            }
        }

        return bitmapOriginal;
    }

    public static Bitmap getResizedImageResource(Context context, int resourceImageId, int width, int height) {
        try {
            // Configura o BitmapFactory para apenas ler o tamanho da imagem (sem carregá-la em memória)
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            // Faz o decode da imagem
            BitmapFactory.decodeResource(context.getResources(), resourceImageId, options);
            // Lê a largura e altura do arquivo
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            if (width == 0 || height == 0) {
                width = outWidth / 2;
                height = outHeight / 2;
            }

            Log.d(TAG, "Resize img, outWidth:" + outWidth + " / outHeight:" + outHeight + ", to outWidth:" + width + " / outHeight:" + height);

            // Fator de escala
            int scaleFactor = Math.min(outWidth / width, outHeight / height);
            // Diz ao decoder para criar uma versão menor da imagem na memória
            options.inSampleSize = scaleFactor;
            Log.d(TAG, "inSampleSize:" + options.inSampleSize);
            // Agora deixa carregar o bitmap completo
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceImageId, options);
            Log.d(TAG, "Resize OK, outWidth:" + bitmap.getWidth() + " / outHeight:" + bitmap.getHeight());

            return bitmap;
        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return null;
    }

    public static Bitmap getResizedImage(Uri uriFile, int width, int height) {
        return getResizedImage(uriFile, width, height, false);
    }

    public static Bitmap getResizedImage(Uri uriFile, int width, int height, boolean fixMatrix) {
        try {
            // Configura o BitmapFactory para apenas ler o tamanho da imagem (sem carregá-la em memória)
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            // Faz o decode da imagem
            BitmapFactory.decodeFile(uriFile.getPath(), options);
            // Lê a largura e altura do arquivo
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            if (width == 0 || height == 0) {
                width = outWidth / 2;
                height = outHeight / 2;
            }

            Log.d(TAG, "Resize img, outWidth:" + outWidth + " / outHeight:" + outHeight + ", to outWidth:" + width + " / outHeight:" + height);

            // Fator de escala
            int scaleFactor = Math.min(outWidth / width, outHeight / height);
            options.inSampleSize = scaleFactor;
            Log.d(TAG, "inSampleSize:" + options.inSampleSize);
            // Agora deixa carregar o bitmap completo
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(uriFile.getPath(), options);

            Log.d(TAG, "Resize OK, outWidth:" + bitmap.getWidth() + " / outHeight:" + bitmap.getHeight());

            if (fixMatrix) {
                Bitmap newBitmap = fixMatrix(uriFile, bitmap);
                bitmap.recycle();
                return newBitmap;
            } else {
                return bitmap;
            }
        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return null;
    }

    private static Bitmap fixMatrix(Uri uriFile, Bitmap bitmap) throws IOException {
        Matrix matrix = new Matrix();

        /**
         * Classe para ler tags escritas no JPEG
         * Para utilizar esta classe precisa de Android 2.2 ou superior
         */
        ExifInterface exif = new ExifInterface(uriFile.getPath());

        // Lê a orientação que foi salva a foto
        int orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);

        boolean fix = false;

        // Rotate bitmap
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                fix = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                fix = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                fix = true;
                break;
            default:
                // ORIENTATION_ROTATE_0
                fix = false;
                break;
        }

        if (!fix) {
            return bitmap;
        }

        // Corrige a orientação (passa a matrix)
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        bitmap.recycle();
        bitmap = null;

        return newBitmap;
    }
}
