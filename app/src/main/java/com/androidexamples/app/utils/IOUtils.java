package com.androidexamples.app.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by marcos on 17/01/2017.
 */

public class IOUtils {
    private static final String TAG = "IOUtils";

    /**
     * Convert InputStream to String
     *
     * @param inputStream
     * @param charset UTF-8 ou ISO-8859-1
     * @return String
     * @throws IOException
     */
    public static String inputStreamToString(InputStream inputStream, String charset) throws IOException {
        byte[] bytes = inputStreamToBytes(inputStream);
        return new String(bytes, charset);
    }

    /**
     * Convert InputStream to byte array
     *
     * @param inputStream
     * @return byte[]
     */
    public static byte[] inputStreamToBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[1024];
            int len;

            while ((len = inputStream.read(buffer)) > 0)
                byteArrayOutputStream.write(buffer, 0, len);

            byte[] bytes = byteArrayOutputStream.toByteArray();
            return bytes;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        } finally {
            try {
                byteArrayOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    /**
     * Write String in OutputStream
     *
     * @param outputStream
     * @param string
     */
    public static void writeString(OutputStream outputStream, String string) {
        writeBytes(outputStream, string.getBytes());
    }

    /**
     * Write byte array in OutputStream
     *
     * @param outputStream
     * @param bytes
     */
    public static void writeBytes(OutputStream outputStream, byte[] bytes) {
        try {
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public static String readString(File file) {
        try {
            if (file == null || !file.exists()) {
                return null;
            }
            InputStream in = new FileInputStream(file);
            String s = inputStreamToString(in, "UTF-8");
            return s;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }
}
