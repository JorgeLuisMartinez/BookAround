package com.example.parcial1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.parcial1.utilidades.utilidades;

public class conexionBD extends SQLiteOpenHelper {


    public conexionBD(@Nullable Context context, @Nullable String name) {
        super(context, name, utilidades.DB_CONTEXT, utilidades.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilidades.Crear_tabla_recordatorios);
        db.execSQL(utilidades.Crear_tabla_reseñas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recordatorios");
        db.execSQL("DROP TABLE IF EXISTS reseñas");
        onCreate(db);
    }
}
