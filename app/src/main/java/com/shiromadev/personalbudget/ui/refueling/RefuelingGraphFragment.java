package com.shiromadev.personalbudget.ui.refueling;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.databinding.FragmentRefuelingExpenseBinding;
import com.shiromadev.personalbudget.databinding.FragmentRefuelingGraphBinding;
import com.shiromadev.personalbudget.tables.ItemTable;
import org.jetbrains.annotations.NotNull;

public class RefuelingGraphFragment extends Fragment {

	TextView statsCosts;
	TextView statsLitres;
	TextView statsAmountRefueling;
	private FragmentRefuelingGraphBinding binding;
	private int costs;
	private float litres;
	private int amountRefueling;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		binding = FragmentRefuelingGraphBinding.inflate(inflater, container, false);
		statsCosts = binding.statsCosts;
		statsLitres = binding.statsLitres;
		statsAmountRefueling = binding.statsAmountRefueling;
		UpdateView();
		return binding.getRoot();
	}

	private void Calculation() {
		costs = 0;
		litres = 0;
		amountRefueling = MainActivity.getRefueling().size();
		for (ItemTable refuel : MainActivity.getRefueling()) {
			costs += refuel.getMoney();
			litres += Float.parseFloat(refuel.getLiters().replace(',', '.'));
		}
	}

	private void UpdateView() {
		Calculation();
		statsCosts.setText(String.valueOf(costs));
		statsLitres.setText(String.valueOf(litres));
		statsAmountRefueling.setText(String.valueOf(amountRefueling));
	}

	@Override
	public void onResume() {
		super.onResume();
		UpdateView();
	}
}