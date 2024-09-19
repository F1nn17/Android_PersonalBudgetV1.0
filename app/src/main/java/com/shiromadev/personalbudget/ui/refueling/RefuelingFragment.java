package com.shiromadev.personalbudget.ui.refueling;

import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.databinding.FragmentRefuelingBinding;
import org.jetbrains.annotations.NotNull;

public class RefuelingFragment extends Fragment implements View.OnClickListener {
	private final String flag = "R";
	private FragmentRefuelingBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		MainActivity.setFlag(flag);
		binding = FragmentRefuelingBinding.inflate(inflater, container, false);
		View root = binding.getRoot();
		Button expenseButt = (Button) root.findViewById(R.id.butt_expense);
		Button graphButt = (Button) root.findViewById(R.id.butt_graph);
		Button settingsButt = (Button) root.findViewById(R.id.butt_setting);
		expenseButt.setOnClickListener(this);
		graphButt.setOnClickListener(this);
		settingsButt.setOnClickListener(this);
		switchChildFragment(new RefuelingExpenseFragment());
		return root;
	}

	@Override
	public void onResume() {
		MainActivity.setFlag(flag);
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	private void switchChildFragment(Fragment fragment) {
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.child_fragment_container, fragment).commit();
	}

	@Override
	public void onClick(View view) {
		int checkId = view.getId();
		if (checkId == R.id.butt_expense) {
			switchChildFragment(new RefuelingExpenseFragment());
		}
		if (checkId == R.id.butt_graph) {
			switchChildFragment(new RefuelingGraphFragment());
		}
		if (checkId == R.id.butt_setting) {
			switchChildFragment(new RefuelingSettingsFragment());
		}
	}
}