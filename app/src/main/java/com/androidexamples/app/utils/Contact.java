package com.androidexamples.app.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;

import java.io.InputStream;
import java.util.List;

/**
 * Created by marcos on 02/01/2017.
 */

public class Contact {

    private long id;
    private String name;
    private Uri photo;
    private List<String> phones;
    private List<String> emails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    // Retorna a URI deste contato, ex: "content://com.android.contacts/contacts/1"
    public Uri getUri() {
        Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
        return uri;
    }

    public Bitmap getPhoto(Context context) {
        Uri uri = getUri();
        ContentResolver contentResolver = context.getContentResolver();
        InputStream inputStream = Contacts.openContactPhotoInputStream(contentResolver, uri);

        if (inputStream != null) {
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }

        return null;
    }
}
