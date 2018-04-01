package edu.css.mgoodson1.cis3334_unit10_particp_vogellasqlite;

/**
 * Created by mgoodson on 3/31/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/** This class creates a MySQL database
 *
 * @author Matt Goodson
 * @version 2018-03-30
 *
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments";     //Table name
    public static final String COLUMN_ID = "_id";               //Primary key
    public static final String COLUMN_COMMENT = "comment";      //Column name

    private static final String DATABASE_NAME = "commments.db"; //Database name
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    /*
     *  CREATE TABLE comments (
     *  _id integer primary key autoincrement,
     *  comment text not null
     *  );
     */
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}