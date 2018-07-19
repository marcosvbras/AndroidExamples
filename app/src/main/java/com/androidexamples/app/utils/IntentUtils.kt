package com.androidexamples.app.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.text.Html
import android.util.Log

/**
 * Created by marcos on 17/01/2017.
 */

object IntentUtils {

    fun openBrowser(context: Context, url: String) {
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {

        }

    }

    fun showVideo(context: Context, url: String) {
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "video/*")
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {

        }

    }

    fun isAvailable(context: Context, intent: Intent): Boolean {
        val mgr = context.packageManager
        val list = mgr.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return list.size > 0
    }

    fun sendEmail(context: Context, toEmail: String, subject: String, message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.type = "plain/text"// message/rfc822
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(toEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(message))
        context.startActivity(intent)
    }
}
