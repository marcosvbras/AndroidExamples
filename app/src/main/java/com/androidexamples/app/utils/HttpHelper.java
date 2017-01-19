package com.androidexamples.app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by marcos on 17/01/2017.
 */

public class HttpHelper {

    private final String TAG = "HttpHelper";
    public final int TIMEOUT_MILLIS = 15000;
    public boolean LOG_ON = false;
    private String contentType;
    private String charsetToEncode;

    public String doGet(String url) throws IOException {
        return doGet(url, null, "UTF-8");
    }

    public String doGet(String urlString, Map<String, String> params, String charset) throws IOException {
        String queryString = getQueryString(params);

        if (queryString != null && queryString.trim().length() > 0)
            urlString += "?" + queryString;

        if (LOG_ON)
            Log.d(TAG, ">> Http.doGet: " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = null;
        String s = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();

            if (contentType != null)
                httpURLConnection.setRequestProperty("Content-Type", contentType);

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(TIMEOUT_MILLIS);
            httpURLConnection.setReadTimeout(TIMEOUT_MILLIS);
            httpURLConnection.connect();
            InputStream inputStream = null;

            int status = httpURLConnection.getResponseCode();

            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                Log.d(TAG, "Error code: " + status);
                inputStream = httpURLConnection.getErrorStream();
            } else {
                inputStream = httpURLConnection.getInputStream();
            }

            s = IOUtils.inputStreamToString(inputStream, charset);

            if (LOG_ON)
                Log.d(TAG, "<< Http.doGet: " + s);

            inputStream.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }

        return s;
    }

    public String doDelete(String url) throws IOException {
        return doDelete(url, null, "UTF-8");
    }

    public String doDelete(String urlString, Map<String, String> params, String charset) throws IOException {
        String queryString = getQueryString(params);

        if (queryString != null && queryString.trim().length() > 0)
            urlString += "?" + queryString;

        if (LOG_ON)
            Log.d(TAG, ">> Http.doDelete: " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = null;
        String s = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();

            if (contentType != null)
                httpURLConnection.setRequestProperty("Content-Type", contentType);

            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setConnectTimeout(TIMEOUT_MILLIS);
            httpURLConnection.setReadTimeout(TIMEOUT_MILLIS);

            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            s = IOUtils.inputStreamToString(inputStream, charset);

            if (LOG_ON)
                Log.d(TAG, "<< Http.doGet: " + s);

            inputStream.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return s;
    }

    public String doPost(String url, Map<String, String> params, String charset) throws IOException {
        String queryString = getQueryString(params);

        byte[] bytes = params != null ? queryString.getBytes(charset) : null;

        if (LOG_ON)
            Log.d(TAG, "Http.doPost: " + url + "?" + params);

        return doPost(url, bytes, charset);
    }

    public String doPost(String url, byte[] params, String charset) throws IOException {
        if (LOG_ON)
            Log.d(TAG, ">> Http.doPost: " + url);

        URL u = new URL(url);
        HttpURLConnection httpURLConnection = null;
        String s = null;

        try {
            httpURLConnection = (HttpURLConnection) u.openConnection();

            if (contentType != null)
                httpURLConnection.setRequestProperty("Content-Type", contentType);

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(TIMEOUT_MILLIS);
            httpURLConnection.setReadTimeout(TIMEOUT_MILLIS);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            if (params != null) {
                OutputStream out = httpURLConnection.getOutputStream();
                out.write(params);
                out.flush();
                out.close();
            }

            InputStream inputStream = null;
            int status = httpURLConnection.getResponseCode();

            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                Log.d(TAG, "Error code: " + status);
                inputStream = httpURLConnection.getErrorStream();
            } else {
                inputStream = httpURLConnection.getInputStream();
            }

            s = IOUtils.inputStreamToString(inputStream, charset);

            if (LOG_ON)
                Log.d(TAG, "<< Http.doPost: " + s);

            inputStream.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }
        return s;
    }

    public Bitmap doGetBitmap(String urlString) throws IOException {
        if (LOG_ON)
            Log.d(TAG, ">> Http.doGet: " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(TIMEOUT_MILLIS);
        httpURLConnection.setReadTimeout(TIMEOUT_MILLIS);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();

        byte[] bytes = IOUtils.inputStreamToBytes(inputStream);

        if (LOG_ON)
            Log.d(TAG, "<< Http.doGet: " + bytes);

        inputStream.close();
        httpURLConnection.disconnect();
        Bitmap bitmap = null;

        if (bytes != null)
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        return bitmap;
    }

    /**
     * Returns QueryString to 'GET'
     */
    public String getQueryString(Map<String, String> params) throws IOException {
        if (params == null || params.size() == 0)
            return null;

        String urlParams = null;

        for (String chave : params.keySet()) {
            Object objectValor = params.get(chave);

            if (objectValor != null) {
                String valor = objectValor.toString();

                if (charsetToEncode != null)
                    valor = URLEncoder.encode(valor, charsetToEncode);

                urlParams = urlParams == null ? "" : urlParams + "&";
                urlParams += chave + "=" + valor;
            }
        }

        return urlParams;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCharsetToEncode(String encode) {
        this.charsetToEncode = encode;
    }
}
