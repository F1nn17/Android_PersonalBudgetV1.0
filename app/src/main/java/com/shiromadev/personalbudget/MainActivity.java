package com.shiromadev.personalbudget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.shiromadev.personalbudget.databinding.ActivityMainBinding;
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

@EqualsAndHashCode(callSuper = true)
@Data
public class MainActivity extends AppCompatActivity {
    enum Months{
        Search(-1,"-1"),
        Jan(1, "Январь"),
        Feb(2, "Февраль"),
        March(3, "Март"),
        Apr(4, "Апрель"),
        May(5, "Мая"),
        June(6, "Июнь"),
        Jule(7, "Июль"),
        Aug(8, "Август"),
        Sept(9, "Сентябрь"),
        Oct(10, "Октябрь"),
        Nov(11, "Ноябрь"),
        Dec(12, "Декабрь");
        private final int id;
        private final String month;

        Months(int id, String month){
            this.id = id;
            this.month = month;
        }

        String getMonths(int id){
            for (Months m:Months.values()) {
                if(m.id == id){
                    return m.month;
                }
            }
            return null;
        }
    }

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private static SQLiteControllerHelper sqlHelper;

    @Getter
    private static ArrayList<ItemTable> balances = new ArrayList<>();

    @Setter
    private static String flag = "I";

    //local date
    private static LocalDateTime date = LocalDateTime.now();
    //current month
    @Getter
    private static int month = date.getMonthValue();

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        ItemTable newItem;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                            assert intent != null;
                            newItem = intent.getSerializableExtra("NEW_ITEM", ItemTable.class);
                        } else {
                            newItem = (ItemTable) intent.getSerializableExtra("NEW_ITEM");
                        }
                        balances.add(newItem);
                        updateBalance();
                        unLoadData();
                    } else {
                        System.out.println("Error receiving!");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            sqlHelper = new SQLiteControllerHelper(getApplicationContext());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        loadData();

        updateBalance();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view -> {
            switch (flag){
                case "I":
                    newIncome();
                    break;
                case "E":
                    newExpense();
                    break;
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_income, R.id.nav_expense, R.id.nav_balance)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void loadData(){
        System.out.println("Start load data database....");
        balances = sqlHelper.unloadTable();
        System.out.println("Load success!");
    }

    public void unLoadData(){
        System.out.println("Start unload data database....");
        sqlHelper.loadTable(balances);
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

    void updateBalance() {
        int income = 0, expense = 0, balance;
        if(!balances.isEmpty()) {
            for (ItemTable item : balances) {
                if (item.getGroup() == ItemTable.GROUP.INCOME) {
                    income += item.getMoney();
                }
                if (item.getGroup() == ItemTable.GROUP.EXPENSE) {
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
            else{
                balances.add(ItemTable.builder()
                        .group(ItemTable.GROUP.BALANCE)
                        .name(Months.Search.getMonths(month))
                        .month(month)
                        .money(balance)
                        .build());
            }
            unLoadData();
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
}