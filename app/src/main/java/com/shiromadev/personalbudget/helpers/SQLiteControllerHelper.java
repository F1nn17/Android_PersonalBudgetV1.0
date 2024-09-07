package com.shiromadev.personalbudget.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import com.shiromadev.personalbudget.controller.SqliteController;

public class SQLiteControllerHelper {

    SQLiteDatabase database;
    Cursor cursor;
    SimpleCursorAdapter cursorAdapter;
    private SqliteController sqliteController;

    public SQLiteControllerHelper(Context context) {
        sqliteController = new SqliteController(context);
    }
}
