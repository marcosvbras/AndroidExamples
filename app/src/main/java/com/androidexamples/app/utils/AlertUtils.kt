package com.androidexamples.app.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast

/**
 * Created by marcos on 17/01/2017.
 */

object AlertUtils {

    /**
     * Alert Dialog with custom view, icon, positive and negative action
     * @return AlertDialog
     */
    fun generateAlertWithCustomView(context: Context, title: String, message: String?, positiveButtonText: String, onPositiveClick: DialogInterface.OnClickListener,
                                    negativeButtonText: String, onNegativeClick: DialogInterface.OnClickListener, view: View, icon: Int): AlertDialog {
        val alertDialogBuilder = AlertDialog.Builder(context)

        if (title != "" && title != null)
            alertDialogBuilder.setTitle(title)

        if (message != "" && message != null)
            alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton(positiveButtonText, onPositiveClick)
        alertDialogBuilder.setNegativeButton(negativeButtonText, onNegativeClick)
        alertDialogBuilder.setView(view)

        if (icon > 0)
            alertDialogBuilder.setIcon(icon)

        return alertDialogBuilder.create()
    }

    /**
     * Dialog with custom layout
     *
     * @param context
     * @param isCancelable
     * @param view
     * @return Dialog
     */
    fun generateDialogWithCustomLayout(context: Context, isCancelable: Boolean, view: View): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(isCancelable)
        dialog.setContentView(view)
        return dialog
    }

    /**
     * Simple AlertDialog with positive button
     */
    fun alert(context: Context, title: String, message: String?, positiveButtonText: String, onPositiveClick: DialogInterface.OnClickListener, icon: Int) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        if (title != "" && title != null)
            alertDialogBuilder.setTitle(title)

        if (message != "" && message != null)
            alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton(positiveButtonText, onPositiveClick)

        if (icon > 0)
            alertDialogBuilder.setIcon(icon)

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    /**
     * Simple AlertDialog with positive and negative buttons
     */
    fun alert(context: Context, title: String, message: String?, positiveButtonText: String, onPositiveClick: DialogInterface.OnClickListener,
              negativeButtonText: String, onNegativeClick: DialogInterface.OnClickListener, icon: Int) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        if (title != "" && title != null)
            alertDialogBuilder.setTitle(title)

        if (message != "" && message != null)
            alertDialogBuilder.setMessage(message)

        if (icon > 0)
            alertDialogBuilder.setIcon(icon)

        alertDialogBuilder.setPositiveButton(positiveButtonText, onPositiveClick)
        alertDialogBuilder.setNegativeButton(negativeButtonText, onNegativeClick)

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun showToastImageView(bitmap: Bitmap, context: Context) {
        val toast = Toast(context)
        val imageView = ImageView(context)
        imageView.setImageBitmap(bitmap)
        toast.view = imageView
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }
}
