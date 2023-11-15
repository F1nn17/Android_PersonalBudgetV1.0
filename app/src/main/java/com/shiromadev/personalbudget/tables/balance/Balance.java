package com.shiromadev.personalbudget.tables.balance;

public class Balance {
    private String month;
    private int balance;

    public Balance(){}
    public Balance(String month, int balance){
        this.month = month;
        this.balance = balance;
    }

    public String getMonth(){
        return this.month;
    }
    public int getBalance(){
        return this.balance;
    }
}
