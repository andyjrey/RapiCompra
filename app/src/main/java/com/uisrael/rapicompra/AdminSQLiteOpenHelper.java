package com.uisrael.rapicompra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="administracion.sqlite";
    public AdminSQLiteOpenHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table registro (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text,apellido text,email text,contrasena text,rep_contrasena text)");
        db.execSQL("create table datos_entrega(_id INTEGEER PRIMARY KEY AUTOINCREMENT, nombre_cliente text,telefono text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
