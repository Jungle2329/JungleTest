package com.zy.jungletest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Junlgle on 2017/3/2.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     *
     */
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "ldcang";


    public DatabaseHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Creates database the first time we try to open it.
     */
    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("create table ldcang ("
                + "id integer primary key autoincrement, "
                + "name text, "
                + "age integer, "
                + "flag boolean)");
    }

    /**
     * Updates the database format when a content provider is used
     * with a database that was created with a different format.
     *
     * Note: to support downgrades, creating a table should always drop it first if it already
     * exists.
     */
    @Override
    public void onUpgrade(final SQLiteDatabase db, int oldV, final int newV) {

        for (int version = oldV + 1; version <= newV; version++) {
            upgradeTo(db, version);
        }
    }

    /**
     * Upgrade database from (version - 1) to version.
     */
    private void upgradeTo(SQLiteDatabase db, int version) {
        switch (version) {
            case 2:
                db.execSQL("ALTER TABLE ldcang ADD phone test"); //往表中增加一列
                break;
            default:
                throw new IllegalStateException("Don't know how to upgrade to " + version);
        }
    }
}
