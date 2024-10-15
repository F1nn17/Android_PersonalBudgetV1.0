package com.shiromadev.personalbudget.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.shiromadev.personalbudget.controller.SqliteController;
import com.shiromadev.personalbudget.tables.ItemTable;

import java.util.ArrayList;

public class SQLiteControllerHelper {
	private final SqliteController sqliteController;
	private SQLiteDatabase database;

	public SQLiteControllerHelper(Context context) {
		sqliteController = new SqliteController(context);
	}

	public void unloadTable(ArrayList<ItemTable> tables) {
		database = sqliteController.getWritableDatabase();
		sqliteController.unloadTable(database, tables);
	}

	public ArrayList<ItemTable> loadTable() {
		database = sqliteController.getReadableDatabase();
		return sqliteController.loadTable(database);
	}
}
