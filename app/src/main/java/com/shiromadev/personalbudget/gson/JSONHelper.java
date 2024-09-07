package com.shiromadev.personalbudget.gson;

import android.content.Context;
import com.google.gson.Gson;
import com.shiromadev.personalbudget.tables.TableList;
import java.io.*;

@Deprecated
public class JSONHelper {

    public static TableList Import(Context context, String nameFile, TableList tableList) {
        try (FileInputStream fileInputStream = context.openFileInput(nameFile);
             InputStreamReader streamReader = new InputStreamReader(fileInputStream)) {
            tableList = new Gson().fromJson(streamReader, TableList.class);
            streamReader.close();
            return tableList;
        } catch (IOException ex) {
            Export(context, nameFile, tableList);
            ex.printStackTrace();
            return Import(context, nameFile, tableList);
        }
    }

    public static boolean Export(Context context, String nameFile, TableList tableList) {
        String jsonString = new Gson().toJson(tableList);
        try (FileOutputStream fileOutputStream =
                     context.openFileOutput(nameFile, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

