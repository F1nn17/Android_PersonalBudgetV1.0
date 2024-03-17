package com.shiromadev.personalbudget.tables;

import java.io.Serializable;

public class TableItems implements Serializable {
    private final String name;
    private int money;
    private int amount;

    public TableItems(String name, int money){
        this.name = name;
        this.money = money;
    }

    public TableItems(String name, int amount, int money){
        this.name = name;
        this.money = money;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

}
