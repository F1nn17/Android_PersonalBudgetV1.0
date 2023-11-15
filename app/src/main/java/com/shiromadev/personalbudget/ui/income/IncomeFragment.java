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
import com.shiromadev.personalbudget.tables.income.Income;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.databinding.FragmentIncomeBinding;

import java.util.ArrayList;

public class IncomeFragment extends Fragment {
    private FragmentIncomeBinding binding;
    private static ArrayList<Income> incomes;

   private TableLayout tableLayout;

    private String flag = "I";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        incomes = MainActivity.getIncomes();
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
        for(int i = 0 ; i < incomes.size(); i++){
            tableRows.add(i, new TableRow(getContext()));
            tableTextName.add(i, new TextView(getContext()));
            tableTextName.get(i).setText(incomes.get(i).getName());
            tableTextName.get(i).setTextSize(22);
            tableTextName.get(i).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRows.get(i).addView(tableTextName.get(i), new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

            tableTextMoney.add(i, new TextView(getContext()));
            tableTextMoney.get(i).setText(String.valueOf(incomes.get(i).getMoney()));
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

    public static void setIncomes(ArrayList<Income> income){
        incomes = income;
    }
}
