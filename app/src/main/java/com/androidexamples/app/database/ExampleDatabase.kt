package com.androidexamples.app.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by marcosvbras on 22/01/17.
 */

class ExampleDatabase(context: Context) {

    private val databaseHelper: DatabaseHelper = DatabaseHelper(context)
    val database: SQLiteDatabase = databaseHelper.writableDatabase

    fun closeConnection() {
        database.close()
        databaseHelper.close()
    }

    fun executeSql(sql: String) {
        database.execSQL(sql)
    }

    private inner class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
            sqLiteDatabase.execSQL(TypeDB.TABLE_CREATE)
            sqLiteDatabase.execSQL(ExampleDB.TABLE_CREATE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        }
    }

    companion object {
        const val DATABASE_NAME = "androidExamples"
        const val DATABASE_VERSION = 3
    }
}
