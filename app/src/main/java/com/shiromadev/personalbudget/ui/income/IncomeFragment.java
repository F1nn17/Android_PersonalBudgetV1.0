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
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.tables.income.Income;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.databinding.FragmentIncomeBinding;

import java.util.ArrayList;

public class IncomeFragment extends Fragment {
    private FragmentIncomeBinding binding;
    private static ArrayList<Income> incomes;

   private TableLayout tableLayout;

    private final String flag = "I";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        incomes = MainActivity.getIncomes();
        MainActivity.setFlag(flag);
        IncomeViewModel incomeViewModel =
                new ViewModelProvider(this).get(IncomeViewModel.class);

        binding = FragmentIncomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tableLayout = binding.tableIncome;
        loadViewIncome();
        return root;
    }

    ArrayList<TableRow> tableRows = new ArrayList<>();
    ArrayList<TextView> tableTextName = new ArrayList<>();
    ArrayList<TextView> tableTextMoney = new ArrayList<>();
    private void loadViewIncome(){
        //create name and money view
        tableRows.add(0, new TableRow(getContext()));
        tableTextName.add(0, new TextView(getContext()));
        tableTextName.get(0).setText(getResources().getString(R.string.table_name_income));
        tableTextName.get(0).setTextSize(22);
        tableTextName.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextName.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

        tableTextMoney.add(0, new TextView(getContext()));
        tableTextMoney.get(0).setText(getResources().getString(R.string.table_money_income));
        tableTextMoney.get(0).setTextSize(22);
        tableTextMoney.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextMoney.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

        tableLayout.addView(tableRows.get(0));

        for(int i = 0 ; i < incomes.size(); i++){
            tableRows.add(i + 1, new TableRow(getContext()));
            tableTextName.add(i + 1, new TextView(getContext()));
            tableTextName.get(i + 1).setText(incomes.get(i).getName());
            tableTextName.get(i + 1).setTextSize(22);
            tableTextName.get(i + 1).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i + 1).addView(tableTextName.get(i + 1), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

            tableTextMoney.add(i + 1, new TextView(getContext()));
            tableTextMoney.get(i + 1).setText(String.valueOf(incomes.get(i).getMoney()));
            tableTextMoney.get(i + 1).setTextSize(22);
            tableTextMoney.get(i + 1).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i + 1).addView(tableTextMoney.get(i + 1), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

            tableLayout.addView(tableRows.get(i + 1));
        }
    }

    @Override
    public void onResume() {
        incomes = MainActivity.getIncomes();
        MainActivity.setFlag(flag);
        tableLayout = binding.tableIncome;
        tableLayout.removeAllViews();
        loadViewIncome();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
