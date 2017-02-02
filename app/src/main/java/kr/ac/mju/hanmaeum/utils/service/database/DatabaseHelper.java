package kr.ac.mju.hanmaeum.utils.service.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import kr.ac.mju.hanmaeum.utils.Constants;

/**
 * Created by Youthink on 2017-02-02.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context) {
        super(context, Constants.BOOKMARK_DATABASE, null, 3);
    }

    @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(getClass().getSimpleName(), "Upgrade Database Version" + i + " to " + i1);
    }
}
