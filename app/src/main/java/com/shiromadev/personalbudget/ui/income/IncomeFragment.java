package com.shiromadev.personalbudget.ui.income;

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
import com.shiromadev.personalbudget.Income;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.MainBudget;
import com.shiromadev.personalbudget.databinding.FragmentIncomeBinding;

import java.util.ArrayList;

public class IncomeFragment extends Fragment {
    private FragmentIncomeBinding binding;
    private final MainBudget mainBudget = MainActivity.getMainBudget();
    private ArrayList<Income> incomes = mainBudget.getIncomes();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        IncomeViewModel incomeViewModel =
                new ViewModelProvider(this).get(IncomeViewModel.class);

        binding = FragmentIncomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TableLayout tableLayout = binding.tableIncome;
        TableRow tableRow2 = new TableRow(getContext());

        TextView textView2 = new TextView(getContext());
        textView2.setText(incomes.get(0).getName());
        textView2.setTextSize(22);
        textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRow2.addView(textView2, new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

        TextView textView3 = new TextView(getContext());
        textView3.setText(String.valueOf(incomes.get(0).getMoney()));
        textView3.setTextSize(22);
        textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRow2.addView(textView3, new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        tableLayout.addView(tableRow2);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
