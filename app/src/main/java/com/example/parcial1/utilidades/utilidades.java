package com.example.parcial1.utilidades;

import android.database.sqlite.SQLiteDatabase;

public class utilidades {
    // CONSTANTES  CAMPOS TABLA RECORDATORIO
    public static final String TABLA_RECORDATORIOS="recordatorios";
    public static final String TABLA_CAMPO_ID="id";
    public static final String TABLA_CAMPO_BIBLIOTECA="biblioteca";
    public static final String TABLA_CAMPO_LIBRO="libro";
    public static final String TABLA_CAMPO_FECHA="fecha";
    public static final String TABLA_CAMPO_HORA="hora";
    // CONSTANTES CAMPOS TABLA RESEÑAS
    public static final String TABLA_RESEÑAS="reseñas";
    public static final String TABLA_CAMPO_IDRS="idrs";
    public static final String TABLA_CAMPO_BIBLIOTECA_Y_LIBRO="biblioteca_y_libro";
    public static final String TABLA_CAMPO_MENSAJE="mensaje";

    public static final int DB_VERSION = 1;
    public static final SQLiteDatabase.CursorFactory DB_CONTEXT = null;


    public static final String Crear_tabla_recordatorios="CREATE TABLE "+TABLA_RECORDATORIOS+" ("+TABLA_CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TABLA_CAMPO_BIBLIOTECA+" TEXT, "+TABLA_CAMPO_LIBRO+" TEXT, "+TABLA_CAMPO_FECHA+" TEXT, "+TABLA_CAMPO_HORA+" TEXT)";
    public static final String Crear_tabla_reseñas="CREATE TABLE reseñas ("+TABLA_CAMPO_IDRS+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TABLA_CAMPO_BIBLIOTECA_Y_LIBRO+" TEXT, "+TABLA_CAMPO_MENSAJE+" TEXT)";

}
