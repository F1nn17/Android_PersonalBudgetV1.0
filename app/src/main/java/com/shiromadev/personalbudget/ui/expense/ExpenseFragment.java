package com.shiromadev.personalbudget.ui.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.databinding.FragmentExpenseBinding;
import com.shiromadev.personalbudget.tables.expense.Expense;
import com.shiromadev.personalbudget.tables.income.Income;

import java.util.ArrayList;

public class ExpenseFragment extends Fragment {
    private FragmentExpenseBinding binding;

    private static ArrayList<Expense> expenses;

    private TableLayout tableLayout;

    private String flag = "E";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        expenses = MainActivity.getExpenses();
        MainActivity.setFlag(flag);
        ExpenseViewModel expenseViewModel =
                new ViewModelProvider(this).get(ExpenseViewModel.class);

        binding = FragmentExpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tableLayout = binding.tableExpense;
        loadViewExpense();
        return root;
    }

    ArrayList<TableRow> tableRows = new ArrayList<>();
    ArrayList<TextView> tableTextProduct = new ArrayList<>();
    ArrayList<TextView> tableTextAmount = new ArrayList<>();
    ArrayList<TextView> tableTextMoney = new ArrayList<>();

    private void loadViewExpense() {
        tableRows.add(0, new TableRow(getContext()));
        tableTextProduct.add(0, new TextView(getContext()));
        tableTextProduct.get(0).setText("Product");
        tableTextProduct.get(0).setTextSize(22);
        tableTextProduct.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextProduct.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

        tableTextAmount.add(0, new TextView(getContext()));
        tableTextAmount.get(0).setText("Amount");
        tableTextAmount.get(0).setTextSize(22);
        tableTextAmount.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextAmount.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.75f));

        tableTextMoney.add(0, new TextView(getContext()));
        tableTextMoney.get(0).setText("Money");
        tableTextMoney.get(0).setTextSize(22);
        tableTextMoney.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextMoney.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

        tableLayout.addView(tableRows.get(0));

        for(int i = 0 ; i < expenses.size(); i++){
            tableRows.add(i + 1, new TableRow(getContext()));
            tableTextProduct.add(i + 1, new TextView(getContext()));
            tableTextProduct.get(i + 1).setText(expenses.get(i).getProduct());
            tableTextProduct.get(i + 1).setTextSize(22);
            tableTextProduct.get(i + 1).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i + 1).addView(tableTextProduct.get(i + 1), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

            tableTextAmount.add(i + 1, new TextView(getContext()));
            tableTextAmount.get(i + 1).setText(String.valueOf(expenses.get(i).getAmount()));
            tableTextAmount.get(i + 1).setTextSize(22);
            tableTextAmount.get(i + 1).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i + 1).addView(tableTextAmount.get(i + 1), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.75f));

            tableTextMoney.add(i + 1, new TextView(getContext()));
            tableTextMoney.get(i + 1).setText(String.valueOf(expenses.get(i).getMoney()));
            tableTextMoney.get(i + 1).setTextSize(22);
            tableTextMoney.get(i + 1).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i + 1).addView(tableTextMoney.get(i + 1), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

            tableLayout.addView(tableRows.get(i + 1));
        }
    }

    @Override
    public void onResume() {
        expenses = MainActivity.getExpenses();
        MainActivity.setFlag(flag);
        tableLayout = binding.tableExpense;
        tableLayout.removeAllViews();
        loadViewExpense();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
