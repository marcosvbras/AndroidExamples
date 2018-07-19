package com.androidexamples.app.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.TypedArray
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager

import com.androidexamples.app.R

/**
 * Created by marcos on 17/01/2017.
 */

object AndroidUtils {

    /**
     * Verify if android version starts with Ice cream sandwich
     * @return boolean
     */
    val isAndroid4ICS: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH

    /**
     * Verify if android version starts with Lollipop
     * @return boolean
     */
    val isAndroid5Lollipop: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    private val RES_IDS_ACTION_BAR_SIZE = intArrayOf(R.attr.actionBarSize)

    /**
     * Verify internet connection
     * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
     * @param context
     * @return boolean
     */
    fun isNetworkAvailable(context: Context): Boolean {
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (connectivityManager == null) {
                return false
            } else {
                val networkInfo = connectivityManager.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnectedOrConnecting
            }
        } catch (e: SecurityException) {
            return false
        }

    }

    /**
     * Close virtual keyboard in fosused view
     * @param context
     * @param view
     */
    fun closeVirtualKeyboard(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Returns true if screen is large or xlarge
     */
    fun isTablet(context: Context): Boolean {
        return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    /**
     * Calculates the Action Bar height in pixels.
     */
    fun getActionBarSize(context: Context?): Int {
        if (context == null)
            return 0

        val currentTheme = context.theme ?: return 0

        val typedArray = currentTheme.obtainStyledAttributes(RES_IDS_ACTION_BAR_SIZE) ?: return 0

        val size = typedArray.getDimension(0, 0f)
        typedArray.recycle()
        return size.toInt()
    }

    /**
     * Returns app version name
     */
    fun getVersionName(activity: Activity): String {
        val packageManager = activity.packageManager
        val packageName = activity.packageName

        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            return "N/A"
        }

    }
}
