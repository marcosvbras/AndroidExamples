package com.androidexamples.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marcosvbras on 22/01/17.
 */

public class ExampleDatabase {

    public static final String DATABASE_NAME = "androidExamples";
    public static final int DATABASE_VERSION = 3;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ExampleDatabase(Context context) {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return sqLiteDatabase;
    }

    public void closeConnection() {
        sqLiteDatabase.close();
        databaseHelper.close();
    }

    public void executeSql(String sql) {
        sqLiteDatabase.execSQL(sql);
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            // Context, database name, factory, database version
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TypeDB.TABLE_CREATE);
            sqLiteDatabase.execSQL(ExampleDB.TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
