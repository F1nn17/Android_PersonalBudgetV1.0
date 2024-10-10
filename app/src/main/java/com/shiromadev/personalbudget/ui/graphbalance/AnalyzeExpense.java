package com.shiromadev.personalbudget.ui.graphbalance;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.api.util.ConvertListKt;
import com.shiromadev.personalbudget.api.util.LoadDataView;
import com.shiromadev.personalbudget.databinding.FragmentAnalyzeExpenseBinding;
import com.shiromadev.personalbudget.tables.ItemTable;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeExpense extends Fragment {
	FragmentAnalyzeExpenseBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = FragmentAnalyzeExpenseBinding.inflate(inflater, container, false);
		LoadDataView.loadViewExpense(getContext(), binding.layoutExpense.tableExpense);
		return binding.getRoot();
	}

	@Override
	public void onStart() {
		super.onStart();
		List<AbstractMap.SimpleEntry<Integer, String>> list = new ArrayList<>();
		for (ItemTable item : MainActivity.getBalances()) {
			if (item.getGroup() == ItemTable.GROUP.EXPENSE || item.getGroup() == ItemTable.GROUP.REFUELING) {
				list.add(new AbstractMap.SimpleEntry<>(item.getMoney(), item.getName()));
			}
		}
		binding.apcexpense.setDataChart(
			ConvertListKt.convert(list)
		);
		binding.apcexpense.startAnimation();
	}

	@Override
	public void onResume() {
		LoadDataView.loadViewExpense(getContext(), binding.layoutExpense.tableExpense);
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}