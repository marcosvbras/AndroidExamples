package com.androidexamples.app.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

import java.util.ArrayList

/**
 * Created by marcosvbras on 22/01/17.
 */

class TypeDB {

    var id: Int = 0
    var type: String? = null

    constructor() {}

    constructor(id: Int, type: String) {
        this.id = id
        this.type = type
    }

    fun save(context: Context): Boolean {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TYPE, type)
        val database = ExampleDatabase(context)
        val result = database.database.insert(TABLE_NAME, null, contentValues)
        database.closeConnection()
        return result == 1L
    }

    fun update(context: Context): Boolean {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TYPE, type)
        val database = ExampleDatabase(context)
        val result = database.database.update(TABLE_NAME, contentValues,
                "$COLUMN_ID = ?", arrayOf(id.toString())).toLong()
        database.database.close()
        database.closeConnection()
        return result == 1L
    }

    fun delete(context: Context): Boolean {
        val database = ExampleDatabase(context)
        val result = database.database.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString())).toLong()
        database.closeConnection()
        return result == 1L
    }

    companion object {

        /**
         * Database section  */

        val TABLE_NAME = "TypeDB"
        val COLUMN_ID = "_id"
        val COLUMN_TYPE = "type"

        val TABLE_CREATE = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_TYPE + " TEXT"
                + ");")

        var columns = arrayOf(COLUMN_ID, COLUMN_TYPE)

        fun list(context: Context): List<TypeDB>? {
            var list: MutableList<TypeDB>? = null
            val database = ExampleDatabase(context)
            val cursor = database.database.query(TABLE_NAME, columns, null, null, null, null, null)

            if (cursor.count > 0) {
                list = ArrayList()

                do {
                    cursor.moveToFirst()
                    val type = TypeDB()
                    type.id = cursor.getInt(0)
                    type.type = cursor.getString(1)
                    list.add(type)
                } while (cursor.moveToNext())
            }

            database.closeConnection()
            return list
        }
    }
}
