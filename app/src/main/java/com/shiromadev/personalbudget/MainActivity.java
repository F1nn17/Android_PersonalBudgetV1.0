package com.shiromadev.personalbudget;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.shiromadev.personalbudget.databinding.ActivityMainBinding;
import com.shiromadev.personalbudget.gson.JSONHelper;
import com.shiromadev.personalbudget.tables.balance.Balance;
import com.shiromadev.personalbudget.tables.expense.Expense;
import com.shiromadev.personalbudget.tables.income.Income;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private static ArrayList<Income> incomes = new ArrayList<>();
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<Balance> balances = new ArrayList<>();


    private int income = 0;
    private int expense = 0;
    private int balance = 0;

    private String flag = "I";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (flag){
                    case "I":
                        Snackbar.make(view, "Add Income", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case "E":
                        Snackbar.make(view, "Add Expense", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                }

                unLoadData();
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

    public void addIncome(Income income){
        incomes.add(income);
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
}