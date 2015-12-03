package com.example.dell.formexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

/**
 * Created by DELL on 02/12/2015.
 */
public class HandlerSql extends SQLiteOpenHelper {
   public  ArrayList listuser= new ArrayList<String>();
    public HandlerSql(Context context){
        super(context,"Mi base",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (" + _ID + " INTEGER PRIMARY KEY," +
                "user TEXT,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);

    }
    public void abrir(){
        this.getWritableDatabase();
    }
    public void cerrar(){
        this.close();

    }


    public  void insetarReg(String user, String password){
        ContentValues valores = new ContentValues();
        valores.put("user",user);
        valores.put("password",password);
        this.getWritableDatabase().insert("usuarios", null, valores);
    }
    public ArrayList leer(){
        String resultado="";
        String columnas[] = {_ID,"user","password"};
        Cursor c = this.getReadableDatabase().query("usuarios",columnas,null,null,null,null,null);
        int id,iu,ip;
        id= c.getColumnIndex(_ID);
        iu=c.getColumnIndex("user");
        ip=c.getColumnIndex("password");

        while(c.moveToNext()!=false){
            listuser.add( c.getString(id)+","+c.getString(iu)+","+c.getString(ip));

        }
        //c.moveToLast();
       // resultado = c.getString(id)+","+c.getString(iu)+","+c.getString(ip);
        return listuser;
    }
}
