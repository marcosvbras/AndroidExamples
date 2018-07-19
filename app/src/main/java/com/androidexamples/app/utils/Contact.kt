package com.androidexamples.app.utils

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract.Contacts

import java.io.InputStream

/**
 * Created by marcos on 02/01/2017.
 */

class Contact {

    var id: Long = 0
    var name: String? = null
    var photo: Uri? = null
    var phones: List<String>? = null
    var emails: List<String>? = null

    // Retorna a URI deste contato, ex: "content://com.android.contacts/contacts/1"
    val uri: Uri
        get() = ContentUris.withAppendedId(Contacts.CONTENT_URI, id)

    fun getPhoto(context: Context): Bitmap? {
        val uri = uri
        val contentResolver = context.contentResolver
        val inputStream = Contacts.openContactPhotoInputStream(contentResolver, uri)

        return if (inputStream != null) {
            BitmapFactory.decodeStream(inputStream)
        } else null

    }
}
