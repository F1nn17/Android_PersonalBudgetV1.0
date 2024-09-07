package com.shiromadev.personalbudget.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import com.shiromadev.personalbudget.controller.SqliteController;
import com.shiromadev.personalbudget.tables.ItemTable;

import java.util.ArrayList;

public class SQLiteControllerHelper {
    SQLiteDatabase database;
    SimpleCursorAdapter cursorAdapter;
    Context context;
    private final SqliteController sqliteController;

    public SQLiteControllerHelper(Context context) {
        sqliteController = new SqliteController(context, database);
        database = sqliteController.openDatabase(database, context);
        sqliteController.onCreate(database);
        this.context = context;
    }

    private boolean openDatabase(){
        database = sqliteController.openDatabase(database, context);
        return database.isOpen();
    }

    public void loadTable(ArrayList<ItemTable> tables){
        if(openDatabase()) sqliteController.loadTable(database,tables);
    }

    public ArrayList<ItemTable> unloadTable(){
        if(openDatabase()) return sqliteController.unloadTable(database);
        else return null;
    }
}
