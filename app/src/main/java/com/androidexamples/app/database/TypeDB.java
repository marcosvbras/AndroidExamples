package com.androidexamples.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosvbras on 22/01/17.
 */

public class TypeDB {

    private int id;
    private String type;

    public TypeDB() {}

    public TypeDB(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Database section */

    public static final String TABLE_NAME = "TypeDB";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TYPE = "type";

    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_TYPE + " TEXT"
            + ");";

    public static String[] columns = new String[] {COLUMN_ID, COLUMN_TYPE};

    public boolean save(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE, type);
        ExampleDatabase database = new ExampleDatabase(context);
        long result = database.getDatabase().insert(TABLE_NAME, null, contentValues);
        database.closeConnection();
        return result == 1;
    }

    public boolean update(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE, type);
        ExampleDatabase database = new ExampleDatabase(context);
        long result = database.getDatabase().update(TABLE_NAME, contentValues,
                COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        database.getDatabase().close();
        database.closeConnection();
        return result == 1;
    }

    public boolean delete(Context context) {
        ExampleDatabase database = new ExampleDatabase(context);
        long result = database.getDatabase().delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        database.closeConnection();
        return result == 1;
    }

    public static List<TypeDB> list(Context context) {
        List<TypeDB> list = null;
        ExampleDatabase database = new ExampleDatabase(context);
        Cursor cursor = database.getDatabase().query(TABLE_NAME, columns, null, null, null, null, null);

        if(cursor.getCount() > 0) {
            list = new ArrayList<>();

            do {
                cursor.moveToFirst();
                TypeDB type = new TypeDB();
                type.setId(cursor.getInt(0));
                type.setType(cursor.getString(1));
                list.add(type);
            } while(cursor.moveToNext());
        }

        database.closeConnection();
        return list;
    }
}
