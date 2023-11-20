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
import androidx.lifecycle.ViewModelProvider;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.databinding.FragmentBalanceBinding;
import com.shiromadev.personalbudget.tables.balance.Balance;
import com.shiromadev.personalbudget.tables.income.Income;
import com.shiromadev.personalbudget.ui.balance.BalanceViewModel;

import java.util.ArrayList;

public class BalanceFragment extends Fragment {
    private FragmentBalanceBinding binding;

    private static ArrayList<Balance> balances;

    private TableLayout tableLayout;

    private String flag = "B";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        balances = MainActivity.getBalances();
        MainActivity.setFlag(flag);
        BalanceViewModel balanceViewModel =
                new ViewModelProvider(this).get(BalanceViewModel.class);

        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        tableLayout = binding.tableBalance;
        loadViewIncome();
        View root = binding.getRoot();


        return root;
    }

    ArrayList<TableRow> tableRows = new ArrayList<>();
    ArrayList<TextView> tableTextName = new ArrayList<>();
    ArrayList<TextView> tableTextMoney = new ArrayList<>();
    private void loadViewIncome(){
        for(int i = 0 ; i < balances.size(); i++){
            tableRows.add(i, new TableRow(getContext()));
            tableTextName.add(i, new TextView(getContext()));
            tableTextName.get(i).setText(balances.get(i).getMonth());
            tableTextName.get(i).setTextSize(22);
            tableTextName.get(i).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i).addView(tableTextName.get(i), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

            tableTextMoney.add(i, new TextView(getContext()));
            tableTextMoney.get(i).setText(String.valueOf(balances.get(i).getBalance()));
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
