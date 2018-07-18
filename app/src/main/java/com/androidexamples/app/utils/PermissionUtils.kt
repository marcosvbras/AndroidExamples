package com.androidexamples.app.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

import java.util.ArrayList

/**
 * Created by marcos on 20/01/2017.
 */
object PermissionUtils {

    /**
     * Sistemas de permissão do Android 6.0
     *
     *
     * http://developer.android.com/preview/features/runtime-permissions.html
     *
     * @param activity
     * @param requestCode
     * @param permissions
     */
    fun validate(activity: Activity, requestCode: Int, vararg permissions: String): Boolean {
        val list = ArrayList<String>()

        for (permission in permissions) {
            if (!checkPermission(activity, permission))
                list.add(permission)
        }

        if (list.isEmpty())
            return true

        val newPermissions = arrayOfNulls<String>(list.size)
        list.toTypedArray()

        ActivityCompat.requestPermissions(activity, newPermissions, 1)
        return false
    }

    fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun isGpsPermissionOk(context: Context): Boolean {
        val ok1 = checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val ok2 = checkPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        return ok1 && ok2
    }
}

