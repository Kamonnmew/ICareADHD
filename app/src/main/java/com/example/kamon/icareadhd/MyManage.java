package com.example.kamon.icareadhd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 2/12/2017 AD.
 */

public class MyManage {

    private Context context;
    private DatabaseUser databaseUser;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {
        this.context = context;
        databaseUser = new DatabaseUser(context);
        sqLiteDatabase = databaseUser.getWritableDatabase();

    }

    public long addValueLanguage(String strLanguage) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseUser.COL_Language, strLanguage);

        return sqLiteDatabase.insert(DatabaseUser.TABLE_Language, null, contentValues);
    }

}   // Main Class
