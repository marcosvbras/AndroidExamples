package com.androidexamples.app.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by marcos on 18/12/2016.
 */
object PreferencesManager {
    // SharedPreferences name/id
    val PREFERENCE_NAME = "androidexamples"
    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    // SharedPreferences mode
    var PRIVATE_MODE = 0

    fun setFirstTimeLaunch(context: Context, isFirstTime: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
    }

    fun isFirstTimeLaunch(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
        val editor = sharedPreferences.edit()
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

    fun setBoolean(context: Context, flag: String, on: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
        val editor = sharedPreferences.edit()
        editor.putBoolean(flag, on)
        editor.commit()
    }

    fun getBoolean(context: Context, flag: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
        return sharedPreferences.getBoolean(flag, true)
    }

    fun setInteger(context: Context, flag: String, valor: Int) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
        val editor = sharedPreferences.edit()
        editor.putInt(flag, valor)
        editor.commit()
    }

    fun getInteger(context: Context, flag: String): Int {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
        return sharedPreferences.getInt(flag, 0)
    }

    fun setString(context: Context, flag: String, valor: String) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
        val editor = sharedPreferences.edit()
        editor.putString(flag, valor)
        editor.commit()
    }

    fun getString(context: Context, flag: String): String {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
        return sharedPreferences.getString(flag, "")
    }
}

