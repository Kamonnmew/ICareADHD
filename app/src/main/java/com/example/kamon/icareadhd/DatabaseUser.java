package com.example.kamon.icareadhd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamon on 26/1/2560.
 */

public class DatabaseUser extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyUser";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "User";
    public static final String COL_TYPE = "type";
    public static final String COL_FNAME = "first_name";
    public static final String COL_LNAME = "last_name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASS = "password";

    private static final String create_language_table = "create table languageTABLE (" +
            "_id integer primary key," +
            "Language text);";


    public DatabaseUser(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TYPE + " TEXT, " + COL_FNAME + " TEXT, "+ COL_LNAME + " TEXT, "+ COL_EMAIL + " TEXT, " + COL_PASS + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_TYPE + ", " + COL_FNAME + ", " + COL_LNAME + ", " + COL_EMAIL + ", "
                + COL_PASS + ") VALUES ('Parent', 'Testfirst', 'Testlast', 'emailtest', 'passwordtest');");

        db.execSQL(create_language_table);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
