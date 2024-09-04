package com.shiromadev.personalbudget.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SqliteController {

    public SqliteController(Context context) {
        SQLiteDatabase database = context.openOrCreateDatabase("database.db", Context.MODE_PRIVATE, null);

        database.close();
    }
}
