package com.shiromadev.personalbudget.ui.graphbalance;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.api.util.ConvertListKt;
import com.shiromadev.personalbudget.databinding.FragmentGraphBalanceBinding;
import com.shiromadev.personalbudget.databinding.FragmentRefuelingGraphBinding;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class GraphBalance extends Fragment {
	private FragmentGraphBalanceBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = FragmentGraphBalanceBinding.inflate(inflater, container, false);
		return binding.getRoot();
	}

	@Override
	public void onStart() {
		super.onStart();
		List<AbstractMap.SimpleEntry<Integer, String>> list = new ArrayList<>();
		list.add(new AbstractMap.SimpleEntry<>(MainActivity.getIncome(), getResources().getString(R.string.menu_income)));
		list.add(new AbstractMap.SimpleEntry<>(MainActivity.getExpense(), getResources().getString(R.string.menu_expense)));
		binding.apcbalance.setBalance(MainActivity.getBalance());
		binding.apcbalance.setDataChart(
			ConvertListKt.convert(list)
		);
		binding.apcbalance.startAnimation();
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}