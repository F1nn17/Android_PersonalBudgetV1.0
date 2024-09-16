package com.shiromadev.personalbudget.api.settings;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RefuelingSetting {
    private final String nameFile = "refueling.json";
    private int price; // rub/l
    private int fuel; // 92,95,98,100,dt
}
