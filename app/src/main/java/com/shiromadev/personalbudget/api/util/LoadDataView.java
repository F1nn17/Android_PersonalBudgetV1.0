package com.shiromadev.personalbudget.api.util;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.shiromadev.personalbudget.MainActivity;
import com.shiromadev.personalbudget.R;
import com.shiromadev.personalbudget.tables.ItemTable;

import java.util.ArrayList;

public abstract class LoadDataView {
	private static final ArrayList<TableRow> tableIncomeRows = new ArrayList<>();
	private static final ArrayList<TextView> tableIncomeTextName = new ArrayList<>();
	private static final ArrayList<TextView> tableIncomeTextMoney = new ArrayList<>();

	private static final ArrayList<TableRow> tableExpenseRows = new ArrayList<>();
	private static final ArrayList<TextView> tableExpenseTextProduct = new ArrayList<>();
	private static final ArrayList<TextView> tableExpenseTextAmount = new ArrayList<>();
	private static final ArrayList<TextView> tableExpenseTextMoney = new ArrayList<>();

	public static void loadViewIncome(Context context, TableLayout tableLayout) {
		tableLayout.removeAllViews();
		//create name and money view
		tableIncomeRows.add(0, new TableRow(context));
		tableIncomeTextName.add(0, new TextView(context));
		tableIncomeTextName.get(0).setText(context.getResources().getString(R.string.table_name_income));
		tableIncomeTextName.get(0).setTextSize(22);
		tableIncomeTextName.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
		tableIncomeRows.get(0).addView(tableIncomeTextName.get(0), new TableRow.LayoutParams(
			TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

		tableIncomeTextMoney.add(0, new TextView(context));
		tableIncomeTextMoney.get(0).setText(context.getResources().getString(R.string.table_money_income));
		tableIncomeTextMoney.get(0).setTextSize(22);
		tableIncomeTextMoney.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
		tableIncomeRows.get(0).addView(tableIncomeTextMoney.get(0), new TableRow.LayoutParams(
			TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

		tableLayout.addView(tableIncomeRows.get(0));

		for (int i = 0; i < MainActivity.getBalances().size(); i++) {
			int k = 1;
			if (MainActivity.getBalances().get(i).getGroup() == ItemTable.GROUP.INCOME) {
				tableIncomeRows.add(k, new TableRow(context));
				tableIncomeTextName.add(k, new TextView(context));
				tableIncomeTextName.get(k).setText(MainActivity.getBalances().get(i).getName());
				tableIncomeTextName.get(k).setTextSize(22);
				tableIncomeTextName.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
				tableIncomeRows.get(k).addView(tableIncomeTextName.get(k), new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

				tableIncomeTextMoney.add(k, new TextView(context));
				tableIncomeTextMoney.get(k).setText(String.valueOf(MainActivity.getBalances().get(i).getMoney()));
				tableIncomeTextMoney.get(k).setTextSize(22);
				tableIncomeTextMoney.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
				tableIncomeRows.get(k).addView(tableIncomeTextMoney.get(k), new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

				tableLayout.addView(tableIncomeRows.get(k));
			}
		}
	}

	public static void loadViewExpense(Context context, TableLayout tableLayout) {
		tableLayout.removeAllViews();
		tableExpenseRows.add(0, new TableRow(context));
		tableExpenseTextProduct.add(0, new TextView(context));
		tableExpenseTextProduct.get(0).setText(context.getResources().getString(R.string.table_product_expense));
		tableExpenseTextProduct.get(0).setTextSize(22);
		tableExpenseTextProduct.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
		tableExpenseRows.get(0).addView(tableExpenseTextProduct.get(0), new TableRow.LayoutParams(
			TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

		tableExpenseTextAmount.add(0, new TextView(context));
		tableExpenseTextAmount.get(0).setText(context.getResources().getString(R.string.table_amount_expense));
		tableExpenseTextAmount.get(0).setTextSize(22);
		tableExpenseTextAmount.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
		tableExpenseRows.get(0).addView(tableExpenseTextAmount.get(0), new TableRow.LayoutParams(
			TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.75f));

		tableExpenseTextMoney.add(0, new TextView(context));
		tableExpenseTextMoney.get(0).setText(context.getResources().getString(R.string.table_money_expense));
		tableExpenseTextMoney.get(0).setTextSize(22);
		tableExpenseTextMoney.get(0).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
		tableExpenseRows.get(0).addView(tableExpenseTextMoney.get(0), new TableRow.LayoutParams(
			TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

		tableLayout.addView(tableExpenseRows.get(0));

		for (int i = 0; i < MainActivity.getBalances().size(); i++) {
			int k = 1;
			if (MainActivity.getBalances().get(i).getGroup() == ItemTable.GROUP.EXPENSE
				|| MainActivity.getBalances().get(i).getGroup() == ItemTable.GROUP.REFUELING) {
				tableExpenseRows.add(k, new TableRow(context));
				tableExpenseTextProduct.add(k, new TextView(context));
				tableExpenseTextProduct.get(k).setText(MainActivity.getBalances().get(i).getName());
				tableExpenseTextProduct.get(k).setTextSize(22);
				tableExpenseTextProduct.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
				tableExpenseRows.get(k).addView(tableExpenseTextProduct.get(k), new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f));

				tableExpenseTextAmount.add(k, new TextView(context));
				tableExpenseTextAmount.get(k).setText(String.valueOf(MainActivity.getBalances().get(i).getAmount()));
				tableExpenseTextAmount.get(k).setTextSize(22);
				tableExpenseTextAmount.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
				tableExpenseRows.get(k).addView(tableExpenseTextAmount.get(k), new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.75f));

				tableExpenseTextMoney.add(k, new TextView(context));
				tableExpenseTextMoney.get(k).setText(String.valueOf(MainActivity.getBalances().get(i).getMoney()));
				tableExpenseTextMoney.get(k).setTextSize(22);
				tableExpenseTextMoney.get(k).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
				tableExpenseRows.get(k).addView(tableExpenseTextMoney.get(k), new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.f));

				tableLayout.addView(tableExpenseRows.get(k));
			}
		}
	}
}
