package com.shiromadev.personalbudget.tables;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Builder
public class ItemTable implements Serializable {
    private final String name;
    private GROUP group;
    private int money;
    private int amount;
    private int month;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ItemTable)) return false;
        ItemTable item = (ItemTable) obj;
        return this.name.equals(item.name);
    }

    @Getter
    public enum GROUP {
        INCOME("income"),
        EXPENSE("expense"),
        BALANCE("balance"),
        REFUELING("refueling");
        private final String GROUP_NAME;
        GROUP(String group_name) {
            GROUP_NAME = group_name;
        }

    }

}
