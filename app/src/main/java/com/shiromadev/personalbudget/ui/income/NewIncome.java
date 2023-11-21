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

    EditText nameEditText;
    EditText moneyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income);
        nameEditText = findViewById(R.id.nameIncome);
        moneyEditText = findViewById(R.id.moneyIncome);
    }

    public void AddIncome(View view){

        String name = nameEditText.getText().toString();
        int money = Integer.parseInt(moneyEditText.getText().toString());
        Income income = new Income(name,money);

        Intent data = new Intent();
        data.putExtra(MainActivity.NEW_INCOME, income);
        setResult(RESULT_OK, data);
        finish();
    }
}
