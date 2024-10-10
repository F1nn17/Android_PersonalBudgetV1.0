package com.shiromadev.personalbudget.ui.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.api.util.LoadDataView;
import com.shiromadev.personalbudget.databinding.FragmentExpenseBinding;
import com.shiromadev.personalbudget.tables.ItemTable;

import java.util.ArrayList;

public class ExpenseFragment extends Fragment {
	private final String flag = "E";
	private FragmentExpenseBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		MainActivity.setFlag(flag);
		binding = FragmentExpenseBinding.inflate(inflater, container, false);
		LoadDataView.loadViewExpense(getContext(), binding.layoutExpense.tableExpense);
		return binding.getRoot();
	}

	@Override
	public void onResume() {
		MainActivity.setFlag(flag);
		LoadDataView.loadViewExpense(getContext(), binding.layoutExpense.tableExpense);
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}
