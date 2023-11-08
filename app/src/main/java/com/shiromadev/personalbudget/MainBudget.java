package com.shiromadev.personalbudget;

import java.util.ArrayList;

public class MainBudget {
    private ArrayList<Income> incomes = new ArrayList<>();


    private int income = 0;
    private int expense = 0;
    private int balance = 0;

    private String flag = "I";

    public MainBudget(){
        Income income1 = new Income("Work_1",200);
        Income income2 = new Income("Work_2",4200);
        Income income3 = new Income("Work_3",2200);

        incomes.add(income1);
        incomes.add(income2);
        incomes.add(income3);
    }

    public ArrayList<Income> getIncomes() {
        return incomes;
    }
}
