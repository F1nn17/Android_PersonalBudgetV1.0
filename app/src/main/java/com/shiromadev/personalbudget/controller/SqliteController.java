package com.shiromadev.personalbudget.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class SqliteController extends SQLiteOpenHelper {
    private static final String NAME_DB = "balance.db";
    private static final int SCHEMA = 1;

    private static final String TABLE_NAME = "balance";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_GROUP = "groups";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_MONTH = "month";

    public SqliteController(@Nullable Context context) {
        super(context, NAME_DB, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_GROUP + " TEXT," +
                COLUMN_NAME + " TEXT," + COLUMN_PRICE + " INTEGER," + COLUMN_AMOUNT + " INTEGER," +
                COLUMN_MONTH + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
