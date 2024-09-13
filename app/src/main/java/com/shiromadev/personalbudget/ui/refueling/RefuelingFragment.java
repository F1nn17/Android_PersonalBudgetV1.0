package com.shiromadev.personalbudget.ui.refueling;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.databinding.FragmentRefuelingBinding;
import com.shiromadev.personalbudget.tables.ItemTable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RefuelingFragment extends Fragment {
    private final String flag = "R";
    ArrayList<TableRow> tableRows = new ArrayList<>();
    ArrayList<TextView> tableTextProduct = new ArrayList<>();
    ArrayList<TextView> tableTextAmount = new ArrayList<>();
    ArrayList<TextView> tableTextMoney = new ArrayList<>();
    private FragmentRefuelingBinding binding;
    private TableLayout tableLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainActivity.setFlag(flag);
        binding = FragmentRefuelingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tableLayout = binding.tableRefueling;
        loadViewRefueling();
        return root;
    }

    private void loadViewRefueling() {
        tableRows.add(0, new TableRow(getContext()));
        tableTextProduct.add(0, new TextView(getContext()));
        tableTextProduct.get(0).setText(getResources().getString(R.string.table_expense_refueling));
        tableTextProduct.get(0).setTextSize(22);
        tableTextProduct.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextProduct.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

        tableTextAmount.add(0, new TextView(getContext()));
        tableTextAmount.get(0).setText(getResources().getString(R.string.table_fuel_refueling));
        tableTextAmount.get(0).setTextSize(22);
        tableTextAmount.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextAmount.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.75f));

        tableTextMoney.add(0, new TextView(getContext()));
        tableTextMoney.get(0).setText(getResources().getString(R.string.table_date_refueling));
        tableTextMoney.get(0).setTextSize(22);
        tableTextMoney.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tableRows.get(0).addView(tableTextMoney.get(0), new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

        tableLayout.addView(tableRows.get(0));

        for (int i = 0; i < MainActivity.getBalances().size(); i++) {
            int k = 1;
            if (MainActivity.getBalances().get(i).getGroup() == ItemTable.GROUP.REFUELING) {
                tableRows.add(k, new TableRow(getContext()));
                tableTextProduct.add(k, new TextView(getContext()));
                tableTextProduct.get(k).setText(String.valueOf(MainActivity.getBalances().get(i).getMoney()));
                tableTextProduct.get(k).setTextSize(22);
                tableTextProduct.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tableRows.get(k).addView(tableTextProduct.get(k), new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

                tableTextAmount.add(k, new TextView(getContext()));
                tableTextAmount.get(k).setText("95");
                tableTextAmount.get(k).setTextSize(22);
                tableTextAmount.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tableRows.get(k).addView(tableTextAmount.get(k), new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.75f));

                tableTextMoney.add(k, new TextView(getContext()));
                tableTextMoney.get(k).setText(MainActivity.getDate().toLocalDate().toString());
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
        tableLayout = binding.tableRefueling;
        tableLayout.removeAllViews();
        loadViewRefueling();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}