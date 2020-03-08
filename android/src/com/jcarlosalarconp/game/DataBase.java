package com.jcarlosalarconp.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBase implements DataBaseInterface {
    private DataBaseOpenHelper openHelper;

    public DataBase(Context c){
        openHelper=new DataBaseOpenHelper(c,1);
    }

    @Override
    public int load() {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c=db.query("levels",
                null,null,null,
                null,null,null);
        if(c.moveToFirst()){//False si no hay ninguna fila, true si hay una
            //Caso en que ya haya una fila
            return c.getInt(c.getColumnIndex("levelsComplete"));
        }else{
            //Si no hay puntuaciones guardadas, empiezo desde 0 levelsComplete
            return 0;
        }
    }

    @Override
    public void save(int newLevelsComplete) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c=db.query("levels",
                null,null,null,
                null,null,null);

        ContentValues cv=new ContentValues();
        cv.put("levels",newLevelsComplete);

        if(c.moveToFirst()){ //False si no hay ninguna fila, true si hay una
            //Caso en que ya haya una fila
            //Siempre voy a tener solo una fila, por tanto, cuando actualizo
            //puedo dejar whereClause y whereArgs a null. Me va a actualizar
            //todas las filas, es decir, la única que existe.
            db.update("levels",cv,null,
                    null);
        }else{
            //Caso en que la tabla esté vacía
            db.insert("levels",null,cv);
        }
        c.close();
        db.close();
    }
}
