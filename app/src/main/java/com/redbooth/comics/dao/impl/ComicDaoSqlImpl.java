package com.redbooth.comics.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.redbooth.comics.dao.AbstractBaseSqliteDao;
import com.redbooth.comics.dao.ComicDao;
import com.redbooth.comics.model.Comic;
import com.redbooth.comics.model.Thumbnail;
import com.redbooth.comics.utils.DBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brahyam on 17/11/2016.
 */

public class ComicDaoSqlImpl extends AbstractBaseSqliteDao implements ComicDao{
    @Override
    public List<Comic> getAllComics() {
        StringBuffer sql = new StringBuffer("" +
                "SELECT * " +
                "FROM "+ DBHandler.COMICS_TABLE_NAME+" "+
                "ORDER BY "+DBHandler.COLUMN_TITLE+" ASC");

        final List<Comic> results = new ArrayList<>();
        query(sql, new DbQueryInterface() {
            @Override
            public void onCursor(Cursor cursor) {
//                Log.d(getClass().getSimpleName(),"getAllComics - query returned "+cursor.getCount()+" elements.");
                // Cache column index.
                int indexTitle = cursor.getColumnIndexOrThrow(DBHandler.COLUMN_TITLE);
                int indexThumbnailPath = cursor.getColumnIndexOrThrow(DBHandler.COLUMN_THUMBNAIL_PATH);
                int indexThumbnailExtension = cursor.getColumnIndexOrThrow(DBHandler.COLUMN_THUMBNAIL_EXTENSION);

                while ( cursor.moveToNext() ){
                    Comic comic = new Comic();
                    comic.setTitle(cursor.getString(indexTitle));
                    Thumbnail thumbnail = new Thumbnail();
                    thumbnail.setPath(cursor.getString(indexThumbnailPath));
                    thumbnail.setExtension(cursor.getString(indexThumbnailExtension));
                    comic.setThumbnail(thumbnail);
                    results.add(comic);
                }
            }
        });

        return results;
    }

    @Override
    public void updateComics(List<Comic> comics) {
        SQLiteDatabase db = DBHandler.getInstance().getWritableDatabase();
        if ( db == null ){
            Log.e(getClass().getSimpleName(),"updateComics - unable to get writable Db");
            return;
        }

        // Clean DB
        db.execSQL(DBHandler.SQL_DELETE_COMICS_TABLE_CONTENT);

        try {
            db.beginTransaction();
            long result;

            for ( Comic comic : comics){
                ContentValues values = new ContentValues();
                values.put(DBHandler.COLUMN_TITLE,comic.getTitle());
                values.put(DBHandler.COLUMN_THUMBNAIL_PATH,comic.getThumbnail().getPath());
                values.put(DBHandler.COLUMN_THUMBNAIL_EXTENSION,comic.getThumbnail().getExtension());
                result = db.insertOrThrow(DBHandler.COMICS_TABLE_NAME,null,values);
                Log.d(getClass().getSimpleName(),"updateComics - inserted comid with id:"+result);
            }
            db.setTransactionSuccessful();
        }
        catch (Exception e){
            Log.e(getClass().getSimpleName(),"updateComics - error inserting comics.");
            e.printStackTrace();
            return;
        }
        finally {
            db.endTransaction();
            Log.d(getClass().getSimpleName(),"updateComics - ended adding comics");
            return;
        }
    }
}
