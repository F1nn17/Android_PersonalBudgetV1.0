package com.shiromadev.personalbudget.ui.settings;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.gson.JSONHelper;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void ClearDataTables(View view) {
//        ArrayList<Income> incomes = new ArrayList<>();
//        ArrayList<Expense> expenses = new ArrayList<>();
//        ArrayList<Balance> balances = new ArrayList<>();
//        boolean resultIncome = JSONHelper.exportIncome(this, incomes);
//        boolean resultExpense = JSONHelper.exportExpense(this, expenses);
//        boolean resultBalance = JSONHelper.exportBalance(this, balances);
//        if (resultIncome) {
//            System.out.println("Данные Income удалены!");
//        }
//        if (resultExpense) {
//            System.out.println("Данные Expense удалены!");
//        }
//        if (resultBalance) {
//            System.out.println("Данные Balance удалены!");
//        }
    }

}