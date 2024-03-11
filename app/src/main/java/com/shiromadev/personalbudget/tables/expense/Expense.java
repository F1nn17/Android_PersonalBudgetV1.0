package com.shiromadev.personalbudget.tables.expense;

import com.shiromadev.personalbudget.tables.Tables;

import java.io.Serializable;

public class Expense extends Tables {
    private int amount;

    public Expense(){ super("", 0);}
    public Expense(String product, int amount, int money){
       super(product, money);
       this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
    public void setAmount(int value){this.amount = value;}
}
