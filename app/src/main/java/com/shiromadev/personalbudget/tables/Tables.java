package com.shiromadev.personalbudget.tables;

import java.io.Serializable;

public abstract class Tables implements Serializable {
    private String name;
    private int money;

    public Tables(String name, int money){
        this.name = name;
        this.money = money;
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
}
