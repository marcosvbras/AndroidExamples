package com.androidexamples.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.androidexamples.app.R;

/**
 * Created by marcos on 17/01/2017.
 */

public class AndroidUtils {

    /**
     * Verify internet connection
     * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     * @param context
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager == null) {
                return false;
            } else {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnectedOrConnecting();
            }
        } catch (SecurityException e) {
            return false;
        }
    }

    /**
     * Close virtual keyboard in fosused view
     * @param context
     * @param view
     */
    public static void closeVirtualKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputMethodManager != null)
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Verify if android version starts with Ice cream sandwich
     * @return boolean
     */
    public static boolean isAndroid4ICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * Verify if android version starts with Lollipop
     * @return boolean
     */
    public static boolean isAndroid5Lollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Returns true if screen is large or xlarge
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private static final int[] RES_IDS_ACTION_BAR_SIZE = {R.attr.actionBarSize};

    /**
     * Calculates the Action Bar height in pixels.
     */
    public static int getActionBarSize(Context context) {
        if (context == null)
            return 0;

        Resources.Theme currentTheme = context.getTheme();

        if (currentTheme == null)
            return 0;

        TypedArray typedArray = currentTheme.obtainStyledAttributes(RES_IDS_ACTION_BAR_SIZE);

        if (typedArray == null)
            return 0;

        float size = typedArray.getDimension(0, 0);
        typedArray.recycle();
        return (int) size;
    }

    /**
     * Returns app version name
     */
    public static String getVersionName(Activity activity) {
        PackageManager packageManager = activity.getPackageManager();
        String packageName = activity.getPackageName();

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "N/A";
        }
    }
}
