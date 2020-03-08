package com.jcarlosalarconp.game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    public DataBaseOpenHelper(Context c, int version){
        super(c,"DataBase",null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table levels(levelsComplete int(3) primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
