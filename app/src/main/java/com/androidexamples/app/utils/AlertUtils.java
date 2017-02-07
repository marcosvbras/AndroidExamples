package com.androidexamples.app.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by marcos on 17/01/2017.
 */

public class AlertUtils {

    /**
     * Alert Dialog with custom view, icon, positive and negative action
     * @return AlertDialog
     */
    public static AlertDialog generateAlertWithCustomView(Context context, String title, String message, String positiveButtonText, DialogInterface.OnClickListener onPositiveClick,
                             String negativeButtonText, DialogInterface.OnClickListener onNegativeClick, View view, int icon) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        if(!title.equals("") && title != null)
            alertDialogBuilder.setTitle(title);

        if(!message.equals("") && message != null)
            alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton(positiveButtonText, onPositiveClick);
        alertDialogBuilder.setNegativeButton(negativeButtonText, onNegativeClick);
        alertDialogBuilder.setView(view);

        if (icon > 0)
            alertDialogBuilder.setIcon(icon);

        return alertDialogBuilder.create();
    }

    /**
     * Dialog with custom layout
     *
     * @param context
     * @param isCancelable
     * @param view
     * @return Dialog
     */
    public static Dialog generateDialogWithCustomLayout(Context context, boolean isCancelable, View view) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(isCancelable);
        dialog.setContentView(view);
        return dialog;
    }

    /**
     * Simple AlertDialog with positive button
     */
    public static void alert(Context context, String title, String message, String positiveButtonText, DialogInterface.OnClickListener onPositiveClick, int icon) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        if(!title.equals("") && title != null)
            alertDialogBuilder.setTitle(title);

        if(!message.equals("") && message != null)
            alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton(positiveButtonText, onPositiveClick);

        if (icon > 0)
            alertDialogBuilder.setIcon(icon);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * Simple AlertDialog with positive and negative buttons
     */
    public static void alert(Context context, String title, String message, String positiveButtonText, DialogInterface.OnClickListener onPositiveClick,
                      String negativeButtonText, DialogInterface.OnClickListener onNegativeClick, int icon) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        if(!title.equals("") && title != null)
            alertDialogBuilder.setTitle(title);

        if(!message.equals("") && message != null)
            alertDialogBuilder.setMessage(message);

        if (icon > 0)
            alertDialogBuilder.setIcon(icon);

        alertDialogBuilder.setPositiveButton(positiveButtonText, onPositiveClick);
        alertDialogBuilder.setNegativeButton(negativeButtonText, onNegativeClick);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void showToastImageView(Bitmap bitmap, Context context) {
        Toast toast = new Toast(context);
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap);
        toast.setView(imageView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
