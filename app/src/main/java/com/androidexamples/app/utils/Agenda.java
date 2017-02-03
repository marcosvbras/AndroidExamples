package com.androidexamples.app.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 02/01/2017.
 */

public class Agenda {
    // content://com.android.contacts/contacts
    private static final Uri URI = Contacts.CONTENT_URI;

    public static Contact getContact(Context context, Uri contactUri) {
        // Busca o contato no banco de dados
        Cursor cursor = context.getContentResolver().query(contactUri, null, null, null, null);

        try {
            if (cursor.moveToNext()) {
                Contact contact = readContact(context, cursor);
                return contact;
            }
        } finally {
            cursor.close();
        }

        return null;
    }

    public static List<Contact> getContacts(Context context) {
        List<Contact> contacts = new ArrayList<>();

        // Recupera o Cursor para percorrer a lista de contacts
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);

        try {
            while (cursor.moveToNext())
                contacts.add(readContact(context, cursor));

        } finally {
            cursor.close();
        }

        return contacts;
    }

    public static Contact readContact(Context context, Cursor cursor) {
        Contact contact = new Contact();
        contact.setId(cursor.getLong(cursor.getColumnIndexOrThrow(Contacts._ID)));
        contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(Contacts.DISPLAY_NAME)));

        // Phone
        boolean hasPhones = "1".equals(cursor.getString(cursor.getColumnIndexOrThrow(Contacts.HAS_PHONE_NUMBER)));

        if (hasPhones)
            contact.setPhones(loadPhones(context, contact.getId()));

        // Email
        contact.setEmails(loadEmails(context, contact.getId()));

        return contact;
    }

    private static List<String> loadEmails(Context context, long id) {
        List<String> emails = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + id, null, null);

        try {
            while (cursor.moveToNext()) {
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                emails.add(cursor.getString(column));
            }
        } finally {
            cursor.close();
        }

        return emails;
    }

    // Busca os telefones na tabela 'ContactsContract.CommonDataKinds.Phone'
    private static List<String> loadPhones(Context context, long id) {
        List<String> phones = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);

        try {
            while (cursor.moveToNext()) {
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                phones.add(cursor.getString(column));
            }
        } finally {
            cursor.close();
        }

        return phones;
    }
}