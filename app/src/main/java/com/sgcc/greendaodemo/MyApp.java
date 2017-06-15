package com.sgcc.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.Stetho;
import com.sgcc.greendaodemo.dto.DaoMaster;
import com.sgcc.greendaodemo.dto.DaoSession;

/**
 * Created by miao_wenlong on 2017/6/15.
 */

public class MyApp extends Application {
    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        helper = new DaoMaster.DevOpenHelper(this, "user_db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
