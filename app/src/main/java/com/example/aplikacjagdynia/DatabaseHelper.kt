package com.example.aplikacjagdynia

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DATABASE_NAME = "comments.db"
private const val DATABASE_VERSION = 2

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE comments (id INTEGER PRIMARY KEY AUTOINCREMENT, comment TEXT NOT NULL, date_added TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db?.execSQL("ALTER TABLE comments ADD COLUMN date_added TEXT NOT NULL DEFAULT 'Unknown'")
        }
    }

    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }
    fun addComment(comment: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("comment", comment)
            put("date_added", getCurrentDateTime())
        }
        db.insert("comments", null, values)
    }

    fun getComments(): List<Comment> {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            "comments", arrayOf("id", "comment", "date_added"), null, null, null, null, null
        )

        val comments = mutableListOf<Comment>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val comment = getString(getColumnIndexOrThrow("comment"))
                val dateAdded = getString(getColumnIndexOrThrow("date_added"))
                comments.add(Comment(id, comment, dateAdded))
            }
        }
        cursor.close()
        return comments
    }
}

data class Comment(val id: Int, val text: String, val dateAdded: String)