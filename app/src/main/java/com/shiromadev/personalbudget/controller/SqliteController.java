package com.shiromadev.personalbudget.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.shiromadev.personalbudget.tables.ItemTable;

import java.util.ArrayList;
import java.util.Arrays;

public class SqliteController extends SQLiteOpenHelper {
    private static final String NAME_DB = "balance.db";
    private static final int SCHEMA = 1;

    private static final String TABLE_NAME = "balances";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_GROUP = "groups";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_MONTH = "month";

    Cursor cursor;

    public SqliteController(@Nullable Context context, SQLiteDatabase db ) {
        super(context, NAME_DB, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Create database table");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_GROUP + " TEXT," +
                COLUMN_NAME + " TEXT," + COLUMN_PRICE + " INTEGER," + COLUMN_AMOUNT + " INTEGER," +
                COLUMN_MONTH + " INTEGER);");
        db.close();
        System.out.println("Create database table success!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public SQLiteDatabase openDatabase(SQLiteDatabase db, Context context){
        return context.openOrCreateDatabase(NAME_DB, Context.MODE_PRIVATE, null);
    }

    private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_GROUP + ", " + COLUMN_NAME + ", "+
            COLUMN_PRICE + ", " + COLUMN_AMOUNT + ", " + COLUMN_MONTH + ") VALUES (?,?,?,?,?)";

    public void loadTable(SQLiteDatabase db, ArrayList<ItemTable> tables){
        try{
            db.beginTransaction();
            db.execSQL(INSERT, tables.toArray());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        finally {
            db.endTransaction();
            db.close();
        }
    }

    public ArrayList<ItemTable> unloadTable(SQLiteDatabase db){
        System.out.println("---------------------------");
        System.out.println("unload data from database to balance table");
        db.beginTransaction();
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        System.out.println();
        db.endTransaction();
        db.close();
        System.out.println("---------------------------");
        return new ArrayList<ItemTable>();
    }


}
