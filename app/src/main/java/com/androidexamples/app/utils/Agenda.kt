package com.androidexamples.app.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import com.androidexamples.app.utils.Agenda.readContact

import java.util.ArrayList

/**
 * Created by marcos on 02/01/2017.
 */

object Agenda {
    // content://com.android.contacts/contacts
    private val URI = Contacts.CONTENT_URI

    fun getContact(context: Context, contactUri: Uri): Contact? {
        // Busca o contato no banco de dados
        val cursor = context.contentResolver.query(contactUri, null, null, null, null)

        try {
            if (cursor!!.moveToNext()) {
                return readContact(context, cursor)
            }
        } finally {
            cursor!!.close()
        }

        return null
    }

    fun getContacts(context: Context): List<Contact> {
        val contacts = ArrayList<Contact>()

        // Recupera o Cursor para percorrer a lista de contacts
        val cursor = context.contentResolver.query(URI, null, null, null, null)

        try {
            while (cursor!!.moveToNext())
                contacts.add(readContact(context, cursor))

        } finally {
            cursor!!.close()
        }

        return contacts
    }

    fun readContact(context: Context, cursor: Cursor): Contact {
        val contact = Contact()
        contact.id = cursor.getLong(cursor.getColumnIndexOrThrow(Contacts._ID))
        contact.name = cursor.getString(cursor.getColumnIndexOrThrow(Contacts.DISPLAY_NAME))

        // Phone
        val hasPhones = "1" == cursor.getString(cursor.getColumnIndexOrThrow(Contacts.HAS_PHONE_NUMBER))

        if (hasPhones)
            contact.phones = loadPhones(context, contact.id)

        // Email
        contact.emails = loadEmails(context, contact.id)

        return contact
    }

    private fun loadEmails(context: Context, id: Long): List<String> {
        val emails = ArrayList<String>()

        val cursor = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + id, null, null)

        try {
            while (cursor!!.moveToNext()) {
                val column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA)
                emails.add(cursor.getString(column))
            }
        } finally {
            cursor!!.close()
        }

        return emails
    }

    // Busca os telefones na tabela 'ContactsContract.CommonDataKinds.Phone'
    private fun loadPhones(context: Context, id: Long): List<String> {
        val phones = ArrayList<String>()

        val cursor = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null)

        try {
            while (cursor!!.moveToNext()) {
                val column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                phones.add(cursor.getString(column))
            }
        } finally {
            cursor!!.close()
        }

        return phones
    }
}