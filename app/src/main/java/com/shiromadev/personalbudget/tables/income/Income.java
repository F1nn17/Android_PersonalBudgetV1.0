package com.shiromadev.personalbudget.tables.income;

public class Income {
    private String name;
    private int money;

    public Income(){}
    public Income(String name, int money){
        this.name = name;
        this.money = money;
    }

    public String getName(){
        return this.name;
    }
    public int getMoney(){
        return this.money;
    }


}
