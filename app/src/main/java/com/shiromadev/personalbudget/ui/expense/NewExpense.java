package com.shiromadev.personalbudget.ui.expense;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.tables.TableItems;

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

        String inpName = nameEditText.getText().toString();
        String outName = inpName.substring(0, 1).toUpperCase() + inpName.substring(1);
        int money = Integer.parseInt(moneyEditText.getText().toString());
        TableItems expense = new TableItems(outName, 1, money);

        Intent data = new Intent();
        data.putExtra(MainActivity.NEW_EXPENSE, expense);
        setResult(RESULT_OK, data);
        finish();
    }
}