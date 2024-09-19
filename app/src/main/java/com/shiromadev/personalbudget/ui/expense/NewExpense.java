package com.shiromadev.personalbudget.ui.expense;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.tables.ItemTable;

import java.util.Arrays;

public class NewExpense extends AppCompatActivity {
	EditText nameEditText;
	EditText moneyEditText;

	private String[] keyWordsEng = {
		"Refueling",
		"Fuel",
		"Gasoline",
		"Diesel"
	};

	private String[] keyWordsRus = {
		"Заправка",
		"Топливо",
		"Бензин",
		"Дизель"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_expense);
		nameEditText = findViewById(R.id.productExpense);
		moneyEditText = findViewById(R.id.moneyExpense);
	}

	public void AddExpense(View view) {

		String inpName = nameEditText.getText().toString();
		String outName = inpName.substring(0, 1).toUpperCase() + inpName.substring(1);
		int money = Integer.parseInt(moneyEditText.getText().toString());
		ItemTable.GROUP group = ItemTable.GROUP.EXPENSE;
		if (Arrays.asList(keyWordsEng).contains(outName)
			|| Arrays.asList(keyWordsRus).contains(outName)) {
			group = ItemTable.GROUP.REFUELING;
		}
		ItemTable expense = ItemTable.builder()
			.group(group)
			.name(outName)
			.amount(1)
			.money(money)
			.month(MainActivity.getMonth())
			.build();
		Intent data = new Intent();
		data.putExtra("NEW_ITEM", expense);
		setResult(RESULT_OK, data);
		finish();
	}
}