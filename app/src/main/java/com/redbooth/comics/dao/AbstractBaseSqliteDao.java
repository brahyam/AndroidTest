package com.redbooth.comics.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.redbooth.comics.utils.DBHandler;

/**
 * Created by Brahyam on 20/11/2016.
 */
public abstract class AbstractBaseSqliteDao {

    protected interface DbQueryInterface {
        void onCursor(Cursor cursor);
    }

    protected void query(String sql, String[] args, DbQueryInterface queryInterface) {

        SQLiteDatabase database = DBHandler.getInstance().getReadableDatabase();
        if (database != null) {
            try {
                final long start = System.currentTimeMillis();
                Cursor result = database.rawQuery(sql, args);
                if (result != null) {
                    try {
                        if (queryInterface != null) {
                            queryInterface.onCursor(result);
                        }
                    } finally {
                        result.close();

                        long elapsedTime = System.currentTimeMillis() - start;

                        // Filter out this tag in logcat
                        int max = Math.min(sql.length(), 60);
                        //Log.d(getClass().getSimpleName(), String.format("Elapsed: %d ms (%s...)", elapsedTime, sql.substring(0, max - 1)));
                    }
                }
            } catch (Exception e) {

                // most likely SQL syntax error: missing column, etc.
                Log.e(getClass().getSimpleName(), e.getMessage());

            } finally {
                database.close();
            }
        }
    }

    public void query(String sql, DbQueryInterface queryInterface) {
        query(sql, null, queryInterface);
    }

    public void query(StringBuffer sql, String[] args, DbQueryInterface queryInterface) {
        query(sql.toString(), args, queryInterface);
    }

    public void query(StringBuffer sql, DbQueryInterface queryInterface) {
        query(sql.toString(), null, queryInterface);
    }

    protected Integer getInt(Cursor cursor, String columnName, Integer defaultValue) {
        try {
            return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    protected Integer getInt(Cursor cursor, String columnName) {
        return getInt(cursor, columnName, null);
    }

    protected String getString(Cursor cursor, String columnName, String defaultValue) {
        try {
            String value = cursor.getString(cursor.getColumnIndexOrThrow(columnName));
            if (value == null || value.length() == 0) {
                return defaultValue;
            } else {
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    protected String getString(Cursor cursor, String columnName) {
        return getString(cursor, columnName, null);
    }
}
