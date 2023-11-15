package com.shiromadev.personalbudget.gson;

import android.content.Context;
import com.google.gson.Gson;
import com.shiromadev.personalbudget.tables.balance.Balance;
import com.shiromadev.personalbudget.tables.expense.Expense;
import com.shiromadev.personalbudget.tables.income.Income;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSONHelper {
    private static final String FILE_Income = "income.json";
    private static final String FILE_Expense = "expense.json";
    private static final String FILE_Balance = "balance.json";

    public static boolean exportIncome(Context context, ArrayList<Income> dataList) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setIncomes(dataList);
        String jsonString = gson.toJson(dataItems);
            try(FileOutputStream fileOutputStream =
                        context.openFileOutput(FILE_Income, Context.MODE_PRIVATE)) {
                fileOutputStream.write(jsonString.getBytes());
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        return false;
    }

    public static ArrayList<Income> importIncome(Context context) {
        try(FileInputStream fileInputStream = context.openFileInput(FILE_Income);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            streamReader.close();
            return  dataItems.getIncomes();
        }
        catch (IOException ex){
            ArrayList<Income> incomes = new ArrayList<>();
            exportIncome(context, incomes);
            return importIncome(context);
        }
    }

    public static boolean exportExpense(Context context, ArrayList<Expense> dataList) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setExpenses(dataList);
        String jsonString = gson.toJson(dataItems);
        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(FILE_Expense, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Expense> importExpense(Context context) {
        try(FileInputStream fileInputStream = context.openFileInput(FILE_Expense);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            streamReader.close();
            return  dataItems.getExpenses();
        }
        catch (IOException ex){
            ArrayList<Expense> expenses = new ArrayList<>();
            exportExpense(context, expenses);
            return importExpense(context);
        }
    }

    public static boolean exportBalance(Context context, ArrayList<Balance> dataList) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setBalances(dataList);
        String jsonString = gson.toJson(dataItems);
        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(FILE_Balance, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Balance> importBalance(Context context) {
        try(FileInputStream fileInputStream = context.openFileInput(FILE_Balance);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            streamReader.close();
            return  dataItems.getBalances();
        }
        catch (IOException ex){
            ArrayList<Balance> balances = new ArrayList<>();
            exportBalance(context, balances);
            return importBalance(context);
        }
    }

    private static class DataItems {
        private ArrayList<Income> incomes;
        private ArrayList<Expense> expenses;
        private ArrayList<Balance> balances;

        ArrayList<Income> getIncomes() {
            return incomes;
        }
        void setIncomes(ArrayList<Income> incomes) {
            this.incomes = incomes;
        }

        ArrayList<Expense> getExpenses() {
            return expenses;
        }
        void setExpenses(ArrayList<Expense> expenses) {
            this.expenses = expenses;
        }

        ArrayList<Balance> getBalances() {
            return balances;
        }
        void setBalances(ArrayList<Balance> balances) {
            this.balances = balances;
        }
    }
}
