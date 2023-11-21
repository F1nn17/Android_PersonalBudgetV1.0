package com.shiromadev.personalbudget.ui.expense;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.tables.expense.Expense;
import com.shiromadev.personalbudget.tables.income.Income;

public class NewExpense extends AppCompatActivity {
    EditText nameEditText;
    EditText moneyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        nameEditText = findViewById(R.id.productExpense);
        moneyEditText = findViewById(R.id.moneyExpense);
    }

    public void AddExpense(View view) {

        String name = nameEditText.getText().toString();
        int money = Integer.parseInt(moneyEditText.getText().toString());
        Expense expense = new Expense(name, 1, money);

        Intent data = new Intent();
        data.putExtra(MainActivity.NEW_EXPENSE, expense);
        setResult(RESULT_OK, data);
        finish();
    }
}