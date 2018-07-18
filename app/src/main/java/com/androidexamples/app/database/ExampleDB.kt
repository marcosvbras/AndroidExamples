package com.androidexamples.app.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

/**
 * Created by marcosvbras on 22/01/17.
 */

class ExampleDB {

    var id: Int = 0
    var name: String? = null
    var weight: Double = 0.toDouble()
    var isActive: Boolean = false
    private var type: TypeDB? = null

    constructor() {}

    constructor(id: Int, name: String, weight: Double, active: Boolean, type: TypeDB) {
        this.id = id
        this.name = name
        this.weight = weight
        this.isActive = active
        this.type = type
    }

    fun save(context: Context): Boolean {
        val database = ExampleDatabase(context)
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, name)
        contentValues.put(COLUMN_WEIGHT, weight)
        contentValues.put(COLUMN_ACTIVE, isActive)
        contentValues.put(COLUMN_TYPE_ID, type?.id)
        val result = database.database.insert(TABLE_NAME, null, contentValues)
        database.closeConnection()
        return result == 1L
    }

    fun getLastEntry() {

    }

    companion object {

        /**
         * Database section  */

        val TABLE_NAME = "Example"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "name"
        val COLUMN_WEIGHT = "weight"
        val COLUMN_ACTIVE = "active"
        val COLUMN_TYPE_ID = "type_id"

        val TABLE_CREATE = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_WEIGHT + " DOUBLE, "
                + COLUMN_ACTIVE + " INTEGER, "
                + COLUMN_TYPE_ID + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_TYPE_ID + ") REFERENCES " + TypeDB.TABLE_NAME + "(" + COLUMN_ID + ")"
                + ");")
    }
}
