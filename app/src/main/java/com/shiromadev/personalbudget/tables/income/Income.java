package com.shiromadev.personalbudget.tables.income;

import com.shiromadev.personalbudget.tables.Tables;

public class Income extends Tables {

    public Income() {
        super("", 0);
    }
    public Income(String name, int money){
        super(name, money);
    }

}
