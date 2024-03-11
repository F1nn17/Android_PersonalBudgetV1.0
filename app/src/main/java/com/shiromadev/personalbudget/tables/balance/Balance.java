package com.shiromadev.personalbudget.tables.balance;

import com.shiromadev.personalbudget.tables.Tables;

public class Balance extends Tables {

    public Balance(){ super("", 0);}
    public Balance(String month, int balance){
        super(month, balance);
    }
}
