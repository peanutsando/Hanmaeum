package kr.ac.mju.hanmaeum.utils.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import kr.ac.mju.hanmaeum.utils.object.shuttle.Shuttle;
import kr.ac.mju.hanmaeum.utils.service.database.BookmarkDatabase;

/**
 * Created by Youthink on 2017-02-07.
 */

public class ShuttleService {

    public static boolean getShuttleServiceCheckList(Context context) {
        BookmarkDatabase database = new BookmarkDatabase();

        ArrayList<Shuttle> shuttles = database.getBookmarkCheck(context);

        long now = System.currentTimeMillis();
        Time time = new Time(now);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String nowTime = simpleDateFormat.format(time);

        String[] setTime = nowTime.split(":");

        for (Shuttle s : shuttles) {
            String databaseTime = s.getStart_time();
            String[] getDbTime = databaseTime.split(":");
            if (s.isBookmark()) {
                Log.d("NOW TIME : ", setTime[0]+"/"+setTime[1]);
                Log.d("BUS TIME : ", getDbTime[0]+"/"+getDbTime[1]);

                if (setTimeCaclulator(setInteger(setTime[0]), setInteger(setTime[1]))
                        == setTimeCaclulator(setInteger(getDbTime[0]), setInteger(getDbTime[1])) -5) {
                    Log.d("TRUE NOW TIME : ", setTime[0]+"/"+setTime[1]);
                    Log.d("TRUE BUS TIME : ", getDbTime[0]+"/"+getDbTime[1]);
                    Log.d("TRUE$$$$", "RETURN TRUE");
                    return true;
                }
            }
        }

        return false;
    }
    private static int setInteger(String x) {
        return Integer.parseInt(x);
    }

    private static int setTimeCaclulator(int a, int b) {
        return (a * 60) + b;
    }
}
