package kr.ac.mju.hanmaeum.utils.service.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.PreferenceManager;
import kr.ac.mju.hanmaeum.utils.object.shuttle.Shuttle;

/**
 * Created by Youthink on 2017-02-02.
 */

public class BookmarkDatabase {

    private SQLiteDatabase database;
    private String[] databaseTableCol = new String[]{
            Constants.TABLE_COL_ID,
            Constants.TABLE_COL_TIME,
            Constants.TABLE_COL_BOOKMARK
    };

    public void createDatabase(Context context) {
        Log.i("Database", "Create Bookmark-Database");

        this.database = context.openOrCreateDatabase(Constants.BOOKMARK_DATABASE, context.MODE_PRIVATE, null);

        database.execSQL(Constants.CREATE_TABLE_QUERY);

        PreferenceManager.setDatabase(context);
    }

    /**
     * Bookmark Update Query
     * If you clicked on the bookmark image, it is a syntax to proceed
     *
     * @param key  : bus index
     * @param flag : bookmark check
     */
    public void setBookmarkCheck(Context context, String key, boolean flag) {
        ContentValues values = new ContentValues();
        if (openDatabase(context)) {
            if (flag) {
                values.put(Constants.TABLE_COL_BOOKMARK, true);
            } else {
                values.put(Constants.TABLE_COL_BOOKMARK, false);
            }
            database.update(Constants.BOOKMARK_TABLE, values, Constants.TABLE_COL_ID + " = ? ", new String[]{key});
        }
    }

    /**
     * Bookmark Select Query
     * If bookmark is true, it is a syntax to proceed
     */
    public ArrayList<Shuttle> getBookmarkCheck(Context context) {
        ArrayList<Shuttle> bookmarkCheck = new ArrayList<>();
        if (openDatabase(context)) {
            Cursor cursor = database.query(
                    true, Constants.BOOKMARK_TABLE
                    , databaseTableCol, null, null,
                    null, null, Constants.TABLE_COL_ID, null
            );

            while (cursor.moveToNext()) {
                bookmarkCheck.add(new Shuttle(cursor.getString(cursor.getColumnIndex(databaseTableCol[0]))
                        , true, cursor.getString(cursor.getColumnIndex(databaseTableCol[2]))));
            }
        }

        return bookmarkCheck;
    }

    /**
     * Bookmark Insert Query
     * If you clicked on the bookmark image when it is false, it is a syntax to proceed
     */
    public void insertBookmark(String id, String time, boolean flag) {
        ContentValues values = new ContentValues();

        values.put(Constants.TABLE_COL_ID, id);
        values.put(Constants.TABLE_COL_TIME, time);
        values.put(Constants.TABLE_COL_BOOKMARK, flag);

        database.insert(Constants.BOOKMARK_TABLE, null, values);
    }

    private boolean openDatabase(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        database = helper.getWritableDatabase();
        return true;
    }
}
