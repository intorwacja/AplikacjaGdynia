package com.example.aplikacjagdynia

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DATABASE_NAME = "comments.db"
private const val DATABASE_VERSION = 1

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE comments (id INTEGER PRIMARY KEY AUTOINCREMENT, comment TEXT NOT NULL)");
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS comments")
        onCreate(db)
    }

    fun addComment(comment: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("comment", comment)
        }
        db.insert("comments", null, values)
    }

    fun getComments(): List<String> {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            "comments", arrayOf("comment"), null, null, null, null, null
        )

        val comments = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                comments.add(getString(getColumnIndexOrThrow("comment")))
            }
        }
        cursor.close()
        return comments
    }
}