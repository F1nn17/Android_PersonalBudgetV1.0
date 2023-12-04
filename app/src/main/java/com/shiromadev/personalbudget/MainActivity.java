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
import com.shiromadev.personalbudget.gson.JSONHelper;
import com.shiromadev.personalbudget.tables.balance.Balance;
import com.shiromadev.personalbudget.tables.expense.Expense;
import com.shiromadev.personalbudget.tables.income.Income;
import com.shiromadev.personalbudget.ui.expense.NewExpense;
import com.shiromadev.personalbudget.ui.income.NewIncome;
import com.shiromadev.personalbudget.ui.settings.SettingActivity;

import java.time.LocalDateTime;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private static ArrayList<Income> incomes = new ArrayList<>();
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<Balance> balances = new ArrayList<>();

    private int income = 0;
    private int expense = 0;
    public static final String NEW_INCOME = "NEW_INCOME";

    private static String flag = "I";
    public static final String NEW_EXPENSE = "NEW_EXPENSE";
    private static int balance = 0;
    LocalDateTime date = LocalDateTime.now();
    int month = date.getMonthValue();
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (Objects.equals(flag, "I")) {
                            Income newIncome;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                                assert intent != null;
                                newIncome = intent.getSerializableExtra(NEW_INCOME, Income.class);
                            } else {
                                newIncome = (Income) intent.getSerializableExtra(NEW_INCOME);
                            }
                            SearchElement(newIncome);
                        }
                        if (Objects.equals(flag, "E")) {
                            Expense newExpense;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                                assert intent != null;
                                newExpense = intent.getSerializableExtra(NEW_EXPENSE, Expense.class);
                            } else {
                                newExpense = (Expense) intent.getSerializableExtra(NEW_EXPENSE);
                            }
                            SearchElement(newExpense);
                        }
                        unLoadData();
                        updateBalance();
                    } else {
                        System.out.println("Error receiving!");
                    }
                }
            });
    HashMap<Integer, String> Months = new HashMap<>();

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
        System.out.println("Данные загружены");
        incomes = JSONHelper.importIncome(this);
        expenses = JSONHelper.importExpense(this);
        balances = JSONHelper.importBalance(this);
    }

    public void unLoadData(){
        boolean resultIncome = JSONHelper.exportIncome(this,incomes);
        boolean resultExpense = JSONHelper.exportExpense(this,expenses);
        boolean resultBalance = JSONHelper.exportBalance(this,balances);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Months.put(1, "Январь");
        Months.put(2, "Февраль");
        Months.put(3, "Март");
        Months.put(4, "Апрель");
        Months.put(5, "Май");
        Months.put(6, "Июнь");
        Months.put(7, "Июль");
        Months.put(8, "Август");
        Months.put(9, "Сентябрь");
        Months.put(10, "Октябрь");
        Months.put(11, "Ноябрь");
        Months.put(12, "Декабрь");

        loadData();

        if (balances.size() >= 2)
        {
            if (!Objects.equals(Months.get(month), balances.get(balances.size() - 2).getMonth()))
            {
                incomes.clear();
                expenses.clear();
                Income pastBalance = new Income(getResources().getString(R.string.previous_month),  balances.get(balances.size() - 2).getBalance());
                incomes.add(pastBalance);
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

    public void newIncome() {
        Intent intent = new Intent(this, NewIncome.class);
        mStartForResult.launch(intent);
    }

    public void newExpense() {
        Intent intent = new Intent(this, NewExpense.class);
        mStartForResult.launch(intent);
    }

    static void SearchElement(Income value){
        boolean be = false;
        int index = 0;
        for(int i = 0; i < incomes.size(); i++){
            if(Objects.equals(incomes.get(i).getName(), value.getName())){
                be = true;
                index = i;
                break;
            }
        }
        if(be){
            incomes.get(index).setMoney(incomes.get(index).getMoney()+value.getMoney());
        }
        else {
            incomes.add(value);
        }
    }
    static void SearchElement(Expense value){
        boolean be = false;
        int index = 0;
        for(int i = 0; i < expenses.size(); i++){
            if(Objects.equals(expenses.get(i).getProduct(), value.getProduct())){
                be = true;
                index = i;
                break;
            }
        }
        if(be){
            expenses.get(index).setMoney(expenses.get(index).getMoney() + value.getMoney());
            expenses.get(index).setAmount(expenses.get(index).getAmount()+1);
        }
        else {
            expenses.add(value);
        }
    }

    void BalanceIncome() {
        if (incomes != null) {
            income = 0;
            for (Income value : incomes) {
                income += value.getMoney();
            }
        }
    }

    void BalanceExpense() {
        if (expenses != null) {
            expense = 0;
            for (Expense value : expenses) {
                expense += value.getMoney();
            }
        }
    }

    public static void setFlag(String flagTable) {
        flag = flagTable;
    }
    public static ArrayList<Income> getIncomes() {
        return incomes;
    }
    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }
    public static ArrayList<Balance> getBalances() {
        return balances;
    }

    void updateBalance() {
        BalanceIncome();
        BalanceExpense();
        balance = income - expense;
        if (balances != null) {
            boolean be = false;
            int index = 0;
            for (int i = 0; i < balances.size(); i++) {
                if (Objects.equals(balances.get(i).getMonth(), Months.get(month))) {
                    be = true;
                    index = i;
                    break;
                }
            }
            if (be) {
                balances.get(index).setBalance(balance);
            } else {
                balances.add(new Balance(Months.get(month), balance));
            }
        } else {
            balances.add(new Balance(Months.get(month), balance));
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