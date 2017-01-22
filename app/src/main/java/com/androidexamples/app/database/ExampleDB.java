package com.androidexamples.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by marcosvbras on 22/01/17.
 */

public class ExampleDB {

    private int id;
    private String name;
    private double weight;
    private boolean active;
    private TypeDB type;

    public ExampleDB() {}

    public ExampleDB(int id, String name, double weight, boolean active, TypeDB type) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.active = active;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Database section */

    public static final String TABLE_NAME = "Example";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_TYPE_ID = "type_id";

    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_WEIGHT + " DOUBLE, "
            + COLUMN_ACTIVE + " INTEGER, "
            + COLUMN_TYPE_ID + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_TYPE_ID + ") REFERENCES " + TypeDB.TABLE_NAME + "(" + COLUMN_ID + ")"
            + ");";

    public boolean save(Context context) {
        ExampleDatabase database = new ExampleDatabase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_WEIGHT, weight);
        contentValues.put(COLUMN_ACTIVE, active);
        contentValues.put(COLUMN_TYPE_ID, type.getId());
        long result = database.getDatabase().insert(TABLE_NAME, null, contentValues);
        database.closeConnection();
        return result == 1;
    }

    public void getLastEntry() {

    }
}
