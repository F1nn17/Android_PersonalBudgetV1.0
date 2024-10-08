package com.shiromadev.personalbudget.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.shiromadev.personalbudget.tables.ItemTable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class SqliteController extends SQLiteOpenHelper {
    private static final String NAME_DB = "balance.db";
    private static final int SCHEMA = 3;

    private static final String TABLE_NAME = "balances";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_GROUP = "groups";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_MONTH = "month";

    String[] headers = new String[]{COLUMN_NAME, COLUMN_GROUP, COLUMN_PRICE, COLUMN_AMOUNT, COLUMN_MONTH};

    public SqliteController(@Nullable Context context) {
        super(context, NAME_DB, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Create database table");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_GROUP + " TEXT," +
                COLUMN_NAME + " TEXT," + COLUMN_PRICE + " INTEGER," + COLUMN_AMOUNT + " INTEGER," +
                COLUMN_MONTH + " INTEGER);");
        System.out.println("Create database table success!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void deleteTable(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public void loadTable(SQLiteDatabase db, ArrayList<ItemTable> tables){
        try{
            deleteTable(db);
            for (ItemTable item : tables) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_GROUP, item.getGroup().getGROUP_NAME());
                values.put(COLUMN_NAME, item.getName());
                values.put(COLUMN_PRICE, item.getMoney());
                values.put(COLUMN_AMOUNT, item.getAmount());
                values.put(COLUMN_MONTH, item.getMonth());
                db.insert(TABLE_NAME, null, values);
                values.clear();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        finally {
            db.close();
        }
    }

    @SuppressLint({"Recycle", "Range"})
    public ArrayList<ItemTable> unloadTable(SQLiteDatabase db){
        ArrayList<ItemTable> tables = new ArrayList<>();
        System.out.println("---------------------------");
        System.out.println("unload data from database to balance table");
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        System.out.println(cursor.getCount());
        System.out.println(cursor);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String searchGroup = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP));
            ItemTable.GROUP group = ItemTable.GROUP.INCOME;
            switch (searchGroup) {
                case "expense":
                    group = ItemTable.GROUP.EXPENSE;
                    break;
                case "balance":
                    group = ItemTable.GROUP.BALANCE;
                    break;
            }
            ItemTable item = ItemTable.builder()
                    .group(group)
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .money(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)))
                    .amount(cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT)))
                    .month(cursor.getInt(cursor.getColumnIndex(COLUMN_MONTH)))
                    .build();
            tables.add(item); //add the item
            cursor.moveToNext();
        }
        db.close();
        System.out.println("---------------------------");
        return tables;
    }


}
