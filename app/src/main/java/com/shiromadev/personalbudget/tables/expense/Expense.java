package com.shiromadev.personalbudget.tables.expense;

import java.io.Serializable;

public class Expense implements Serializable {
    private String product;
    private int amount;
    private int money;

    public Expense(){}
    public Expense(String product, int amount, int money){
        this.product = product;
        this.amount = amount;
        this.money = money;
    }
    public String getProduct(){
        return this.product;
    }
    public int getAmount(){
        return this.amount;
    }
    public int getMoney(){
        return this.money;
    }

    public void setMoney(int value){this.money = value;}
    public void setAmount(int value){this.amount = value;}
}
