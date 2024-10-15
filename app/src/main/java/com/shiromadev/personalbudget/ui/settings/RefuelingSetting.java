package com.shiromadev.personalbudget.ui.settings;

import lombok.Data;

@Data
public class RefuelingSetting {
	private final String nameFile = "refueling.json";
	private float price; // rub/l
	private String fuel; // 92,95,98,100,dt
	private String[] fuels = {"92", "95", "98", "100", "ДТ"};

}
