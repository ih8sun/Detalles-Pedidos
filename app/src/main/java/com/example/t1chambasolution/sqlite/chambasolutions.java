package com.example.t1chambasolution.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class chambasolutions extends SQLiteOpenHelper {


    private static final String nameDB = "chambasolution.db";
    private static final int versionDB = 1;

    private static final String createTableUsuario = "create table if not exists Usuario (id integer, correo varchar(255), clave varchar(255))";
    private static final String dropTableUsuario = "drop table if exists Usuario";

    //contructor
    public chambasolutions(@Nullable Context context) {
        super(context, nameDB, null, versionDB );

    }

    //metodos
    @Override
    public void onCreate(SQLiteDatabase db) {
        //aca se creara el esquema de la bd

        db.execSQL(createTableUsuario);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //aca se actualizara el esquema de la bd

        db.execSQL(dropTableUsuario);
        db.execSQL(createTableUsuario);

    }

    public boolean agregarUsuario(int id, String correo, String clave){

        SQLiteDatabase db = getWritableDatabase();
        if(db != null ) {
            db.execSQL("insert int Usuario values(" + id + ", '" + correo + "', '" + clave + "');");
            db.close();
            return true;

        }

        return false;

    }
}