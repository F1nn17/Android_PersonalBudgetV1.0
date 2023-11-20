package com.shiromadev.personalbudget.ui.income;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.gson.JSONHelper;
import com.shiromadev.personalbudget.tables.income.Income;

import java.io.Serializable;
import java.util.ArrayList;

public class NewIncome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income);
    }

    public void AddIncome(View view){
        EditText nameEditText = findViewById(R.id.nameIncome);
        EditText moneyEditText = findViewById(R.id.moneyIncome);
        String name = nameEditText.getText().toString();
        int money = Integer.parseInt(moneyEditText.getText().toString());
        Income income = new Income(name,money);
        MainActivity.addIncome(income);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
