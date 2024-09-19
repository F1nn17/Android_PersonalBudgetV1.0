package com.shiromadev.personalbudget.helpers;

import android.content.Context;
import com.google.gson.Gson;
import com.shiromadev.personalbudget.ui.settings.RefuelingSetting;

import java.io.*;

public class JSONHelper {

	protected static boolean existFile(Context context, RefuelingSetting setting) {
		try {
			File file = new File(context.getFilesDir(), setting.getNameFile());
			if (file.exists()) {
				return true;
			} else {
				if (file.createNewFile()) {
					return export(context, setting);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getLocalizedMessage());
		}
		return false;
	}

	public static boolean export(Context context, RefuelingSetting setting) {
		if (existFile(context, setting)) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(setting);
			try (FileOutputStream fileOutputStream =
					 context.openFileOutput(setting.getNameFile(), Context.MODE_PRIVATE)) {
				fileOutputStream.write(jsonString.getBytes());
				return true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getLocalizedMessage());
			}
		}
		return false;
	}

	public static RefuelingSetting importSetting(Context context) {
		RefuelingSetting setting = new RefuelingSetting();
		setting.setFuel("92");
		setting.setPrice(51.26f);
		if (existFile(context, setting)) {
			try (FileInputStream fileInputStream = context.openFileInput(setting.getNameFile());
				 InputStreamReader streamReader = new InputStreamReader(fileInputStream)) {
				Gson gson = new Gson();
				setting = gson.fromJson(streamReader, RefuelingSetting.class);
				return setting;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.out.println(e.getLocalizedMessage());
			}
		}
		return setting;
	}

}
