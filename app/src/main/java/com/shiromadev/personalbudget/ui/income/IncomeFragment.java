package com.shiromadev.personalbudget.ui.income;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.tables.ItemTable;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.databinding.FragmentIncomeBinding;

import java.util.ArrayList;

public class IncomeFragment extends Fragment {
    private FragmentIncomeBinding binding;

    private TableLayout tableLayout;

    private final String flag = "I";

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainActivity.setFlag(flag);
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

        for (int i = 0; i < MainActivity.getBalances().size(); i++) {
            int k = 1;
            if (MainActivity.getBalances().get(i).getGroup() == ItemTable.GROUP.INCOME) {
                tableRows.add(k, new TableRow(getContext()));
                tableTextName.add(k, new TextView(getContext()));
                tableTextName.get(k).setText(MainActivity.getBalances().get(i).getName());
                tableTextName.get(k).setTextSize(22);
                tableTextName.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tableRows.get(k).addView(tableTextName.get(k), new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

                tableTextMoney.add(k, new TextView(getContext()));
                tableTextMoney.get(k).setText(String.valueOf(MainActivity.getBalances().get(i).getMoney()));
                tableTextMoney.get(k).setTextSize(22);
                tableTextMoney.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tableRows.get(k).addView(tableTextMoney.get(k), new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

                tableLayout.addView(tableRows.get(k));
            }
        }
    }

    @Override
    public void onResume() {
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
