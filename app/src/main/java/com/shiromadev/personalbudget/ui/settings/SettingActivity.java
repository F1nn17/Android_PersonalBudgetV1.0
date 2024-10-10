package com.shiromadev.personalbudget.ui.settings;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.databinding.ActivitySettingBinding;
import com.shiromadev.personalbudget.tables.ItemTable;
import com.shiromadev.personalbudget.ui.refueling.RefuelingExpenseFragment;
import com.shiromadev.personalbudget.ui.refueling.RefuelingGraphFragment;
import com.shiromadev.personalbudget.ui.refueling.RefuelingSettingsFragment;

import java.util.Random;

public class SettingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
	}

	public void ClearDataTables(View view) {
		MainActivity.getBalances().clear();
		MainActivity.getRefueling().clear();
		finish();
	}

	public void backToMain(View view) {
		finish();
	}

	public void fullingIncomes(View view) {
		char name = 'А';
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			ItemTable item = ItemTable.builder()
				.group(ItemTable.GROUP.INCOME)
				.name(String.valueOf(Character.toChars(name + i)))
				.money(random.nextInt(10000 - 500) + 250)
				.build();
			MainActivity.getBalances().add(item);
		}
	}

	public void fullingExpenses(View view) {
		char name = 'А';
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			ItemTable item = ItemTable.builder()
				.group(ItemTable.GROUP.EXPENSE)
				.name(String.valueOf(Character.toChars(name + i)))
				.amount(1)
				.money(random.nextInt(5000 - 50) + 25)
				.build();
			MainActivity.getBalances().add(item);
		}
	}

}