package com.shiromadev.personalbudget.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.shiromadev.personalbudget.controller.SqliteController;
import com.shiromadev.personalbudget.tables.ItemTable;

import java.util.ArrayList;

public class SQLiteControllerHelper {
    private SQLiteDatabase database;

    private final SqliteController sqliteController;

    public SQLiteControllerHelper(Context context) {
        sqliteController = new SqliteController(context);
    }

    public void loadTable(ArrayList<ItemTable> tables){
        database = sqliteController.getWritableDatabase();
        sqliteController.loadTable(database, tables);
    }

    public ArrayList<ItemTable> unloadTable(){
        database = sqliteController.getReadableDatabase();
        return sqliteController.unloadTable(database);
    }
}
