package com.shiromadev.personalbudget.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.tables.ItemTable;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class SqliteController extends SQLiteOpenHelper {
	private static final String NAME_DB = "balance.db";
	private static final int SCHEMA = 7;

	private static final String TABLE_NAME = "balances";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_GROUP = "groups";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_PRICE = "price";
	private static final String COLUMN_AMOUNT = "amount";
	private static final String COLUMN_MONTH = "month";
	private static final String COLUMN_DATA = "data";
	private static final String COLUMN_LITRES = "litres";

	private static final String COLUMNS = COLUMN_GROUP + " , " + COLUMN_NAME + " , " + COLUMN_PRICE + " , "
		+ COLUMN_AMOUNT + " , " + COLUMN_MONTH + " , " + COLUMN_DATA + " , " + COLUMN_LITRES;

	String[] headers = new String[]{COLUMN_NAME, COLUMN_GROUP, COLUMN_PRICE, COLUMN_AMOUNT, COLUMN_MONTH, COLUMN_DATA, COLUMN_LITRES};

	public SqliteController(@Nullable Context context) {
		super(context, NAME_DB, null, SCHEMA);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("Create database table");
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
			"( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_GROUP + " TEXT," +
			COLUMN_NAME + " TEXT," + COLUMN_PRICE + " INTEGER," + COLUMN_AMOUNT + " INTEGER," +
			COLUMN_MONTH + " INTEGER," + COLUMN_DATA + " TEXT," + COLUMN_LITRES + " TEXT);");
		System.out.println("Create database table success!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public void unloadTable(SQLiteDatabase db, ArrayList<ItemTable> tables) {
		try (db) {
			if (!tables.isEmpty()) {
				db.execSQL("DELETE FROM " + TABLE_NAME);
				ContentValues values = new ContentValues(tables.size());
				for (ItemTable item : tables) {
					values.put(COLUMN_GROUP, item.getGroup().getGROUP_NAME());
					values.put(COLUMN_NAME, item.getName());
					values.put(COLUMN_PRICE, item.getMoney());
					values.put(COLUMN_AMOUNT, item.getAmount());
					values.put(COLUMN_MONTH, item.getMonth());
					values.put(COLUMN_DATA, String.valueOf(item.getData()));
					values.put(COLUMN_LITRES, item.getLiters());
					db.insert(TABLE_NAME, null, values);
					values.clear();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}

	@SuppressLint({"Recycle", "Range"})
	public ArrayList<ItemTable> loadTable(SQLiteDatabase db) {
		ArrayList<ItemTable> tables = new ArrayList<>();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		System.out.println("Всего записей = " + cursor.getCount());
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String searchGroup = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP));
			ItemTable.GROUP group = switch (searchGroup) {
				case "expense" -> ItemTable.GROUP.EXPENSE;
				case "balance" -> ItemTable.GROUP.BALANCE;
				case "refueling" -> ItemTable.GROUP.REFUELING;
				default -> ItemTable.GROUP.INCOME;
			};
			String dataString = cursor.getString(cursor.getColumnIndex(COLUMN_DATA));
			LocalDateTime data;
			if (dataString.equals("null")) data = null;
			else data = LocalDateTime.parse(dataString);
			tables.add(createItem(group,
				cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
				cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)),
				cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT)),
				cursor.getInt(cursor.getColumnIndex(COLUMN_MONTH)),
				data,
				cursor.getString(cursor.getColumnIndex(COLUMN_LITRES))));
			cursor.moveToNext();
		}
		db.close();
		return tables;
	}

	private ItemTable createItem(ItemTable.GROUP group, String name, int money, int amount, int month, LocalDateTime data, String liters) {
		return ItemTable.builder()
			.group(group)
			.name(name)
			.money(money)
			.amount(amount)
			.month(month)
			.data(data)
			.liters(liters)
			.build();
	}


}
