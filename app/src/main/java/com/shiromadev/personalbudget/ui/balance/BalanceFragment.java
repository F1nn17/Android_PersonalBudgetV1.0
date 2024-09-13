package com.shiromadev.personalbudget.ui.balance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.databinding.FragmentBalanceBinding;
import com.shiromadev.personalbudget.tables.ItemTable;

import java.util.ArrayList;

public class BalanceFragment extends Fragment {
    private FragmentBalanceBinding binding;

    private TableLayout tableLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        String flag = "B";
        MainActivity.setFlag(flag);
        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        tableLayout = binding.tableBalance;
        loadViewIncome();
        return binding.getRoot();
    }

    ArrayList<TableRow> tableRows = new ArrayList<>();
    ArrayList<TextView> tableTextMonth = new ArrayList<>();
    ArrayList<TextView> tableTextMoney = new ArrayList<>();
    private void loadViewIncome(){
        //create name and money view
        tableRows.add(0, new TableRow(getContext()));
        tableTextMonth.add(0, new TextView(getContext()));
        tableTextMonth.get(0).setText(getResources().getString(R.string.table_month_balance));
        tableTextMonth.get(0).setTextSize(22);
        tableTextMonth.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextMonth.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

        tableTextMoney.add(0, new TextView(getContext()));
        tableTextMoney.get(0).setText(getResources().getString(R.string.table_balance_balance));
        tableTextMoney.get(0).setTextSize(22);
        tableTextMoney.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextMoney.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

        tableLayout.addView(tableRows.get(0));
        
        for (int i = 0; i < MainActivity.getBalances().size(); i++) {
            int k = 0;
            if (MainActivity.getBalances().get(i).getGroup() == ItemTable.GROUP.BALANCE) {
                tableRows.add(k, new TableRow(getContext()));
                tableTextMonth.add(0, new TextView(getContext()));
                tableTextMonth.get(k).setText(MainActivity.getBalances().get(i).getName());
                tableTextMonth.get(k).setTextSize(22);
                tableTextMonth.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tableRows.get(k).addView(tableTextMonth.get(k), new TableRow.LayoutParams(
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
        tableLayout = binding.tableBalance;
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
