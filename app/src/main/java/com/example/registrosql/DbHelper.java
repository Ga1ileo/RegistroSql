package com.example.registrosql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UProperty;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("create Table Usuarios(name TEXT primary key, contact TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
        Db.execSQL("drop Table if exists Usuarios");
    }

    public Boolean Guardar(String name, String contact, String dob){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);

        long result = Db.insert("Usuarios", null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean Actualizar(String name, String contact, String dob){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);

        Cursor cursor = Db.rawQuery("select * from Usuarios where name = ?", new String[]{name});

        if(cursor.getCount()>0) {

            long result = Db.update("Usuarios", contentValues, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }else {
            return false;
        }
    }

    public Boolean Eliminar(String name){

        SQLiteDatabase Db = this.getWritableDatabase();
        Cursor cursor = Db.rawQuery("select * from Usuarios where name = ?", new String[]{name});

        if(cursor.getCount()>0) {

            long result = Db.delete("Usuarios", "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }else {
            return false;
        }
    }

    public Cursor Getdata(){

        SQLiteDatabase Db = this.getWritableDatabase();
        Cursor cursor = Db.rawQuery("select * from Usuarios", null);

        return cursor;
    }


}
