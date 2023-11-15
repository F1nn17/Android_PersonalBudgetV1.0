package com.shiromadev.personalbudget.tables.expense;

public class Expense {
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

}
