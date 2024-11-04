package com.shiromadev.personalbudget.ui.graphbalance;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.databinding.FragmentAnalyzeBalanceBinding;
import org.jetbrains.annotations.NotNull;

public class AnalyzeBalance extends Fragment implements View.OnClickListener {

	private FragmentAnalyzeBalanceBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = FragmentAnalyzeBalanceBinding.inflate(inflater, container, false);
		View root = binding.getRoot();
		ImageButton incomeButt = (ImageButton) root.findViewById(R.id.butt_income);
		ImageButton expenseButt = (ImageButton) root.findViewById(R.id.butt_expense);
		ImageButton balanceButt = (ImageButton) root.findViewById(R.id.butt_balance);
		incomeButt.setOnClickListener(this);
		expenseButt.setOnClickListener(this);
		balanceButt.setOnClickListener(this);
		switchChildFragment(new GraphBalance());
		return root;
	}

	private void switchChildFragment(Fragment fragment) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.child_fragment_container, fragment).commit();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	@Override
	public void onClick(View view) {
		int checkId = view.getId();
		if (checkId == R.id.butt_income) {
			switchChildFragment(new AnalyzeIncome());
		}
		if (checkId == R.id.butt_expense) {
			switchChildFragment(new AnalyzeExpense());
		}
		if (checkId == R.id.butt_balance) {
			switchChildFragment(new GraphBalance());
		}
	}
}