package com.androidexamples.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by marcos on 18/12/2016.
 */
public class Preferences {
    // SharedPreferences name/id
    public static final String PREFERENCE_NAME = "androidexamples";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    // SharedPreferences mode
    public static int PRIVATE_MODE = 0;

    public static void setFirstTimeLaunch(Context context, boolean isFirstTime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public static boolean isFirstTimeLaunch(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public static void setBoolean(Context context, String flag, boolean on) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(flag, on);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String flag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        boolean value = sharedPreferences.getBoolean(flag, true);
        return value;
    }

    public static void setInteger(Context context, String flag, int valor) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(flag, valor);
        editor.commit();
    }

    public static int getInteger(Context context, String flag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        int value = sharedPreferences.getInt(flag, 0);
        return value;
    }

    public static void setString(Context context, String flag, String valor) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(flag, valor);
        editor.commit();
    }

    public static String getString(Context context, String flag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        String value = sharedPreferences.getString(flag, "");
        return value;
    }
}

