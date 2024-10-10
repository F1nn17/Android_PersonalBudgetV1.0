package com.shiromadev.personalbudget.ui.graphbalance;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.api.util.ConvertListKt;
import com.shiromadev.personalbudget.api.util.LoadDataView;
import com.shiromadev.personalbudget.databinding.FragmentAnalyzeIncomeBinding;
import com.shiromadev.personalbudget.tables.ItemTable;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeIncome extends Fragment {
	FragmentAnalyzeIncomeBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = FragmentAnalyzeIncomeBinding.inflate(inflater, container, false);
		LoadDataView.loadViewIncome(getContext(), binding.layoutIncome.tableIncome);
		return binding.getRoot();
	}

	@Override
	public void onStart() {
		super.onStart();
		List<AbstractMap.SimpleEntry<Integer, String>> list = new ArrayList<>();
		for (ItemTable item : MainActivity.getBalances()) {
			if (item.getGroup() == ItemTable.GROUP.INCOME) {
				list.add(new AbstractMap.SimpleEntry<>(item.getMoney(), item.getName()));
			}
		}
		binding.apcincome.setDataChart(
			ConvertListKt.convert(list)
		);
		binding.apcincome.startAnimation();
	}

	@Override
	public void onResume() {
		LoadDataView.loadViewIncome(getContext(), binding.layoutIncome.tableIncome);
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}