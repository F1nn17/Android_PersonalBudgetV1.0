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
import com.shiromadev.personalbudget.controller.SqliteController;
import com.shiromadev.personalbudget.databinding.ActivityMainBinding;
import com.shiromadev.personalbudget.gson.JSONHelper;
import com.shiromadev.personalbudget.tables.TableItems;
import com.shiromadev.personalbudget.tables.TableList;
import com.shiromadev.personalbudget.ui.expense.NewExpense;
import com.shiromadev.personalbudget.ui.income.NewIncome;
import com.shiromadev.personalbudget.ui.settings.SettingActivity;

import java.time.LocalDateTime;
import java.util.*;

enum Months{
    Search(0, ""),
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

    private int getId(){return id;}
    String getMonth(int id){
        for (Months m:Months.values()) {
            System.out.println(m.id);
            if(m.id == id){
                System.out.println("good");
                return m.month;
            }
        }
        return null;
    }
}

public class MainActivity extends AppCompatActivity {

    private SqliteController sqliteController;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private static TableList incomeList = new TableList();
    private static TableList expenseList = new TableList();
    private static TableList balanceList = new TableList();
    public static final String NEW_INCOME = "NEW_INCOME";
    private static String flag = "I";
    public static final String NEW_EXPENSE = "NEW_EXPENSE";
    LocalDateTime date = LocalDateTime.now();
    int month = date.getMonthValue(); //current month
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqliteController = new SqliteController(this);
        loadData();
        if (balanceList.size() >= 2)
        {
            if (!Objects.equals(Months.Search.getMonth(month), balanceList.get(balanceList.size() - 2).getName()))
            {
                incomeList.clear();
                expenseList.clear();
                TableItems pastBalance = new TableItems(getResources().getString(R.string.previous_month),  balanceList.get(balanceList.size() - 2).getMoney());
                incomeList.add(pastBalance);
                updateBalance();
                unLoadData();
            }
        }
        updateBalance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (flag){
                    case "I":
                        newIncome();
                        break;
                    case "E":
                        newExpense();
                        break;
                }
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_income, R.id.nav_expense, R.id.nav_balance)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (Objects.equals(flag, "I")) {
                            TableItems newIncome;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                                assert intent != null;
                                newIncome = intent.getSerializableExtra(NEW_INCOME, TableItems.class);
                            } else {
                                newIncome = (TableItems) intent.getSerializableExtra(NEW_INCOME);
                            }
                            incomeList.add(newIncome);
                        }
                        if (Objects.equals(flag, "E")) {
                            TableItems newExpense;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                                assert intent != null;
                                newExpense = intent.getSerializableExtra(NEW_EXPENSE, TableItems.class);
                            } else {
                                newExpense = (TableItems) intent.getSerializableExtra(NEW_EXPENSE);
                            }
                           expenseList.add(newExpense);
                        }
                        updateBalance();
                        unLoadData();
                    } else {
                        System.out.println("Error receiving!");
                    }
                }
            });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        incomeList = JSONHelper.Import(this, "Income.json", incomeList);
        expenseList = JSONHelper.Import(this, "Expense.json", expenseList);
        balanceList =  JSONHelper.Import(this, "Balance.json", balanceList);
        System.out.println("Данные загружены!");
    }

    public void unLoadData(){
        boolean resultIncome = JSONHelper.Export(this, "Income.json", incomeList);
        boolean resultExpense = JSONHelper.Export(this, "Expense.json", expenseList);
        boolean resultBalance = JSONHelper.Export(this, "Balance.json", balanceList);
        if(resultIncome){
            System.out.println("Данные Income сохранены!");
        }
        if(resultExpense){
            System.out.println("Данные Expense сохранены!");
        }
        if(resultBalance){
            System.out.println("Данные Balance сохранены!");
        }
    }

    public void newIncome() {
        Intent intent = new Intent(this, NewIncome.class);
        mStartForResult.launch(intent);
    }

    public void newExpense() {
        Intent intent = new Intent(this, NewExpense.class);
        mStartForResult.launch(intent);
    }

    public static void setFlag(String flagTable) {
        flag = flagTable;
    }

    void updateBalance() {
        try {
            int income = incomeList.sum();
            int expense = expenseList.sum();
            int balance = income - expense;
            if (balanceList.size() != 0) {
                boolean be = false;
                int index = 0;
                for (int i = 0; i < balanceList.size(); i++) {
                    if (Objects.equals(balanceList.get(i).getName(), Months.Search.getMonth(month))) {
                        be = true;
                        index = i;
                        break;
                    }
                }
                if(be) {
                   balanceList.get(index).setMoney(balance);
                } else {
                   balanceList.add(new TableItems(Months.Search.getMonth(month), balance));
                }
            } else {
                balanceList.add(new TableItems(Months.Search.getMonth(month), balance));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static TableList getIncomeList() {
        return incomeList;
    }
    public static TableList getExpenseList() {
        return expenseList;
    }
    public static TableList getBalanceList() {
        return balanceList;
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