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
        ExpenseViewModel expenseViewModel =
                new ViewModelProvider(this).get(ExpenseViewModel.class);

        binding = FragmentExpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tableLayout = binding.tableExpense;
        loadViewIncome();
        return root;
    }

    ArrayList<TableRow> tableRows = new ArrayList<>();
    ArrayList<TextView> tableTextProduct = new ArrayList<>();
    ArrayList<TextView> tableTextAmount = new ArrayList<>();
    ArrayList<TextView> tableTextMoney = new ArrayList<>();
    private void loadViewIncome(){
        for(int i = 0 ; i < expenses.size(); i++){
            tableRows.add(i, new TableRow(getContext()));
            tableTextProduct.add(i, new TextView(getContext()));
            tableTextProduct.get(i).setText(expenses.get(i).getProduct());
            tableTextProduct.get(i).setTextSize(22);
            tableTextProduct.get(i).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i).addView(tableTextProduct.get(i), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

            tableTextAmount.add(i, new TextView(getContext()));
            tableTextAmount.get(i).setText(String.valueOf(expenses.get(i).getAmount()));
            tableTextAmount.get(i).setTextSize(22);
            tableTextAmount.get(i).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i).addView(tableTextAmount.get(i), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.75f));

            tableTextMoney.add(i, new TextView(getContext()));
            tableTextMoney.get(i).setText(String.valueOf(expenses.get(i).getMoney()));
            tableTextMoney.get(i).setTextSize(22);
            tableTextMoney.get(i).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i).addView(tableTextMoney.get(i), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

            tableLayout.addView(tableRows.get(i));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
