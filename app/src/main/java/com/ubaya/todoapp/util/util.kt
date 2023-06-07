package com.ubaya.todoapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.todoapp.model.TodoDatabase

val DB_NAME = "tododb"

fun buildDb(context: Context):TodoDatabase{
    val db = Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_2_3)
        .build()

    return db
}

val MIGRATION_1_2 = object:Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL"
        )

        database.execSQL(
            "INSERT INTO todo(title, notes, priority) VALUES('Sample', 'Sample notes', 3)")
    }
}

val MIGRATION_2_3 = object:Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        /*Answer: is_done use Integer because SQL Lite doesn't provide boolean storage class.
        Boolean stored as values as integers 0 (false) and 1 (true)*/
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL"
        )

        database.execSQL(
            "INSERT INTO todo(title, notes, priority, is_done) VALUES('Sample', 'Sample notes', 3, 0)")
    }
}