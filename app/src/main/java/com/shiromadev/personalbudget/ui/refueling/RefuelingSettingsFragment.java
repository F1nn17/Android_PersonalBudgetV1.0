package com.shiromadev.personalbudget.ui.refueling;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.databinding.FragmentRefuelingSettingsBinding;
import org.jetbrains.annotations.NotNull;

public class RefuelingSettingsFragment extends Fragment {
	private Spinner spinnerFuels;
	private EditText editPrice;
	private TextView fuelView;
	private TextView priceView;
	private Button editButton;
	private Button acceptButton;
	private ArrayAdapter<String> adapter;

	private static void hideKeyboardFrom(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		FragmentRefuelingSettingsBinding binding = FragmentRefuelingSettingsBinding.inflate(inflater, container, false);
		View root = binding.getRoot();
		spinnerFuels = binding.spinnerFuels;
		editPrice = binding.editPrice;
		fuelView = binding.fuelView;
		priceView = binding.priceView;
		editButton = binding.editButton;
		acceptButton = binding.acceptButton;
		adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
			MainActivity.getRefuelingSetting().getFuels());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerFuels.setAdapter(adapter);
		editPrice = binding.editPrice;

		loadSettings();

		editButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				editPrice.setVisibility(View.VISIBLE);
				spinnerFuels.setVisibility(View.VISIBLE);
				acceptButton.setVisibility(View.VISIBLE);
				fuelView.setVisibility(View.INVISIBLE);
				priceView.setVisibility(View.INVISIBLE);
				editButton.setVisibility(View.INVISIBLE);
				spinnerFuels.setSelection(adapter.getPosition(fuelView.getText().toString()));
			}
		});

		acceptButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				editPrice.setVisibility(View.INVISIBLE);
				spinnerFuels.setVisibility(View.INVISIBLE);
				acceptButton.setVisibility(View.INVISIBLE);
				fuelView.setVisibility(View.VISIBLE);
				priceView.setVisibility(View.VISIBLE);
				editButton.setVisibility(View.VISIBLE);
				MainActivity.getRefuelingSetting().setFuel(
					spinnerFuels.getSelectedItem().toString()
				);
				MainActivity.getRefuelingSetting().setPrice(
					Float.parseFloat(editPrice.getText().toString())
				);
				loadSettings();
				MainActivity.saveSettings();
				hideKeyboardFrom(root.getContext(), v);
			}
		});

		return root;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void loadSettings() {
		fuelView.setText(MainActivity.getRefuelingSetting().getFuel());
		priceView.setText(String.valueOf(MainActivity.getRefuelingSetting().getPrice()));
		editPrice.setText(String.valueOf(MainActivity.getRefuelingSetting().getPrice()));
	}

}