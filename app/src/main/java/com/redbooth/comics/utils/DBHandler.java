package com.redbooth.comics.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Brahyam on 20/11/2016.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DBHandler";
    private static DBHandler instance;

    private static Context appContext;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "comics.db";

    // Contract //
    public static final String COMICS_TABLE_NAME = "comics";

    // Columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_THUMBNAIL_PATH = "thumbnail_path";
    public static final String COLUMN_THUMBNAIL_EXTENSION = "thumbnail_extension";

    // Statements
    public static final String SQL_CREATE_COMICS_TABLE = ""+
            "CREATE TABLE "+ COMICS_TABLE_NAME +" ("+
            COLUMN_ID+" INTEGER PRIMARY KEY,"+
            COLUMN_TITLE +" TEXT,"+
            COLUMN_THUMBNAIL_PATH +" TEXT,"+
            COLUMN_THUMBNAIL_EXTENSION +" TEXT )";

    public static final String SQL_DELETE_COMICS_TABLE_CONTENT = "" +
            "DELETE FROM "+ COMICS_TABLE_NAME;

    public static final String SQL_DELETE_COMICS_TABLE = ""+
            "DROP TABLE IF EXISTS "+ COMICS_TABLE_NAME;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COMICS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_COMICS_TABLE);
        onCreate(db);
    }

    /**
     * Initializes the context to be used for DB operations
     * @param context context to be used for all db operations
     */
    public static void init(Context context) {
        Log.d(LOG_TAG, "init - SQL");
        appContext = context.getApplicationContext();
    }

    /**
     * Returns an instance capable of handling DB operations using initialization context.
     * @return DBHandler instance
     */
    public static DBHandler getInstance() {
        if (instance == null) {
            if (appContext == null) {
                Log.e(LOG_TAG, "getInstance - App Context NULL");
                return null;
            }
            instance = new DBHandler(appContext);
        }
        return instance;
    }

}
