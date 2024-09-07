package com.shiromadev.personalbudget.tables;

@Deprecated
public class TableArray {
    ItemTable[] arrays;

    TableArray() {
        arrays = new ItemTable[999];
    }

    TableArray(int length) {
        arrays = new ItemTable[length];
    }

    public void Add(ItemTable item) {
        try {
            int i = 0;
            while (arrays[i] != null) {
                i++;
            }
            arrays[i] = item;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
