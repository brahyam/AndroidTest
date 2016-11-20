package com.redbooth.comics;

import android.app.Application;

import com.redbooth.comics.utils.DBHandler;

/**
 * Created by Brahyam on 20/11/2016.
 */

public class AppController extends Application {

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize DB.
        DBHandler.init(this);
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}
