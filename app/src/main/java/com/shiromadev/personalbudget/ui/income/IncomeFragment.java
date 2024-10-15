package com.shiromadev.personalbudget.ui.income;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.shiromadev.personalbudget.api.util.LoadDataView;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.databinding.FragmentIncomeBinding;

public class IncomeFragment extends Fragment {
	private final String flag = "I";
	private FragmentIncomeBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		MainActivity.setFlag(flag);
		binding = FragmentIncomeBinding.inflate(inflater, container, false);
		LoadDataView.loadViewIncome(getContext(), binding.layoutIncome.tableIncome);
		return binding.getRoot();
	}

	@Override
	public void onResume() {
		MainActivity.setFlag(flag);
		LoadDataView.loadViewIncome(getContext(), binding.layoutIncome.tableIncome);
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}
