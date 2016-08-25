package org.alex_z.app.a4pdareader.additional.greendao;

import android.content.Context;

import org.alex_z.app.a4pdareader.data.entity.DaoMaster;
import org.alex_z.app.a4pdareader.data.entity.DaoSession;
import org.greenrobot.greendao.database.Database;


public class GreenDaoProvider {
    private static boolean encrypted = false;
    private static GreenDaoProvider instance;

    private DaoSession daoSession;

    public static GreenDaoProvider getInstance() {
        if (instance == null) throw new NullPointerException();
        return instance;
    }

    public static void initialization(Context context) {
        initialization(context, false);
    }

    public static void initialization(Context context, boolean encrypted) {
        if (instance == null) {
            GreenDaoProvider.encrypted = encrypted;
            instance = new GreenDaoProvider(context);
        }
    }

    public static void release() {
        instance = null;
        encrypted = false;
    }

    private GreenDaoProvider(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, encrypted ? "notes-db-encrypted" : "notes-db");
        Database db = encrypted ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
