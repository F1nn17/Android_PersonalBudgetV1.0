package com.shiromadev.personalbudget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.shiromadev.personalbudget.ui.settings.RefuelingSetting;
import com.shiromadev.personalbudget.databinding.ActivityMainBinding;
import com.shiromadev.personalbudget.helpers.JSONHelper;
import com.shiromadev.personalbudget.helpers.SQLiteControllerHelper;
import com.shiromadev.personalbudget.tables.ItemTable;
import com.shiromadev.personalbudget.ui.expense.NewExpense;
import com.shiromadev.personalbudget.ui.income.NewIncome;
import com.shiromadev.personalbudget.ui.settings.SettingActivity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

import static androidx.navigation.Navigation.findNavController;

@EqualsAndHashCode(callSuper = true)
@Data
public class MainActivity extends AppCompatActivity {
	@SuppressLint("StaticFieldLeak")
	@Getter
	static Context context;
	@Getter
	private static ArrayList<ItemTable> refueling = new ArrayList<>();
	//local date
	@Getter
	private static LocalDateTime date = LocalDateTime.now();
	private static SQLiteControllerHelper sqlHelper;
	@Getter
	private static ArrayList<ItemTable> balances = new ArrayList<>();
	@Setter
	private static String flag = "I";
	//current month
	@Getter
	private static int month = date.getMonthValue();
	@Getter
	private static RefuelingSetting refuelingSetting = new RefuelingSetting();
	private AppBarConfiguration mAppBarConfiguration;
	private ActivityMainBinding binding;
	private TextView tvBalance;
	//get result other activity
	ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
		@Override
		public void onActivityResult(ActivityResult result) {
			if (result.getResultCode() == Activity.RESULT_OK) {
				Intent intent = result.getData();
				ItemTable newItem;
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
					assert intent != null;
					newItem = intent.getSerializableExtra("NEW_ITEM", ItemTable.class);
				} else {
					newItem = (ItemTable) intent.getSerializableExtra("NEW_ITEM");
				}
				addItem(newItem);
				updateBalance();
				unLoadData();
			} else {
				System.out.println("Error receiving!");
			}
		}
	});
	private TextView tvMonth;

	public static void saveSettings() {
		JSONHelper.export(context, refuelingSetting);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		try {
			sqlHelper = new SQLiteControllerHelper(getApplicationContext());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
		loadData();
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		NavigationView navigationView = binding.navView;
		View hv = navigationView.getHeaderView(0);
		tvBalance = hv.findViewById(R.id.balance_view);
		tvMonth = hv.findViewById(R.id.month_view);
		tvMonth.setText(Months.Search.getMonths(month));
		updateBalance();
		setContentView(binding.getRoot());
		setSupportActionBar(binding.appBarMain.toolbar);
		binding.appBarMain.fab.setOnClickListener(view -> {
			switch (flag) {
				case "I":
					newIncome();
					break;
				case "E":
					newExpense();
					break;
			}
		});
		DrawerLayout drawer = binding.drawerLayout;
		mAppBarConfiguration = new AppBarConfiguration.Builder(
			R.id.nav_income, R.id.nav_expense, R.id.nav_balance,
			R.id.nav_refueling)
			.setOpenableLayout(drawer)
			.build();
		NavController navController = findNavController(this, R.id.nav_host_fragment_content_main);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = findNavController(this, R.id.nav_host_fragment_content_main);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
			|| super.onSupportNavigateUp();
	}

	public void loadData() {
		System.out.println("Start load data database....");
		balances = sqlHelper.unloadTable(month);
		refuelingSetting = JSONHelper.importSetting(this);
		System.out.println("Load success!");
	}

	public void unLoadData() {
		System.out.println("Start unload data database....");
		sqlHelper.loadTable(balances);
		JSONHelper.export(this, refuelingSetting);
		System.out.println("Unload success!");
	}

	public void newIncome() {
		Intent intent = new Intent(this, NewIncome.class);
		mStartForResult.launch(intent);
	}

	public void newExpense() {
		Intent intent = new Intent(this, NewExpense.class);
		mStartForResult.launch(intent);
	}

	@SuppressLint("SetTextI18n")
	void updateBalance() {
		int income = 0, expense = 0, balance = 0;
		if (!balances.isEmpty()) {
			for (ItemTable item : balances) {
				if (item.getGroup() == ItemTable.GROUP.INCOME) {
					income += item.getMoney();
				}
				if (item.getGroup() == ItemTable.GROUP.EXPENSE) {
					expense += item.getMoney();
				}
				if (item.getGroup() == ItemTable.GROUP.REFUELING) {
					expense += item.getMoney();
				}
			}
			balance = income - expense;
			int index = 0;
			while (index < balances.size()
				&& (balances.get(index).getGroup() != ItemTable.GROUP.BALANCE
				|| balances.get(index).getMonth() != month)) {
				index++;
			}
			if (balances.size() == 1) index--;
			if (balances.get(index).getGroup() == ItemTable.GROUP.BALANCE)
				balances.get(index).setMoney(balance);
			else {
				balances.add(ItemTable.builder()
					.group(ItemTable.GROUP.BALANCE)
					.name(Months.Search.getMonths(month))
					.month(month)
					.money(balance)
					.build());
			}
			unLoadData();
		}
		tvBalance.setText(balance + " ₽");
	}

	private void addItem(ItemTable item) {
		boolean isSuccess = false;
		for (ItemTable balance : balances) {
			if (balance.equals(item)) {
				balance.setMoney(balance.getMoney() + item.getMoney());
				if (balance.getGroup() == ItemTable.GROUP.EXPENSE) balance.setAmount(balance.getAmount() + 1);
				if (balance.getGroup() == ItemTable.GROUP.REFUELING) balance.setAmount(balance.getAmount() + 1);
				isSuccess = true;
			}
		}
		if (!isSuccess) balances.add(item);
		if (item.getGroup() == ItemTable.GROUP.REFUELING) {
			refueling.add(item);
		}
	}

	@Override
	protected void onStop() {
		unLoadData();
		super.onStop();
	}

	public void SettingView(MenuItem item) {
		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
	}

	enum Months {
		Search(-1, "-1"),
		Jan(1, MainActivity.context.getResources().getString(R.string.title_month_january)),
		Feb(2, MainActivity.context.getResources().getString(R.string.title_month_february)),
		March(3, MainActivity.context.getResources().getString(R.string.title_month_march)),
		Apr(4, MainActivity.context.getResources().getString(R.string.title_month_april)),
		May(5, MainActivity.context.getResources().getString(R.string.title_month_may)),
		June(6, MainActivity.context.getResources().getString(R.string.title_month_june)),
		Jule(7, MainActivity.context.getResources().getString(R.string.title_month_july)),
		Aug(8, MainActivity.context.getResources().getString(R.string.title_month_august)),
		Sept(9, MainActivity.context.getResources().getString(R.string.title_month_september)),
		Oct(10, MainActivity.context.getResources().getString(R.string.title_month_october)),
		Nov(11, MainActivity.context.getResources().getString(R.string.title_month_november)),
		Dec(12, MainActivity.context.getResources().getString(R.string.title_month_december));

		private final int id;
		private final String month;

		Months(int id, String month) {
			this.id = id;
			this.month = month;
		}

		String getMonths(int id) {
			for (Months m : Months.values()) {
				if (m.id == id) {
					return m.month;
				}
			}
			return null;
		}
	}
}