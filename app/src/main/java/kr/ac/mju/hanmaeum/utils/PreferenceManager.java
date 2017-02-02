package kr.ac.mju.hanmaeum.utils;

import android.content.SharedPreferences;
import android.content.Context;
import android.util.Log;


/**
 * Created by Youthink on 2017-02-02.
 */

public class PreferenceManager {
    private static SharedPreferences pref;
    private static SharedPreferences.Editor edit;

    public static void setDatabase(Context context){
        Log.i("Preference", "Apply from Preference to TRUE");
        pref = context.getSharedPreferences(Constants.DATABASE_CREATED_PREF, context.MODE_PRIVATE);
        edit = pref.edit();
        edit.putBoolean(Constants.DATABASE_CREATED_PREF, true);
        edit.apply();
    }

    public static void setShuttleDatabase(Context context){
        Log.i("Preference", "Apply from Preference to TRUE");
        pref = context.getSharedPreferences(Constants.DATABASE_INIT_PREF, context.MODE_PRIVATE);
        edit = pref.edit();
        edit.putBoolean(Constants.DATABASE_INIT_PREF, true);
        edit.apply();
    }

    public static boolean getDatabase(Context context){
        pref = context.getSharedPreferences(Constants.DATABASE_CREATED_PREF, context.MODE_PRIVATE);
        return pref.getBoolean(Constants.DATABASE_CREATED_PREF, false);
    }

    public static boolean getShuttleDatabase(Context context){
        pref = context.getSharedPreferences(Constants.DATABASE_INIT_PREF, context.MODE_PRIVATE);
        return pref.getBoolean(Constants.DATABASE_INIT_PREF, false);
    }
}
