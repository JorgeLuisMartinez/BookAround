package com.example.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.parcial1.entidades.recordatorios;
import com.example.parcial1.entidades.reseñas;
import com.example.parcial1.utilidades.utilidades;

public class agregar_resenas extends AppCompatActivity {

    EditText biblioteca_y_libro, mensaje;
    ImageButton agregarresena;
    reseñas rese;
    conexionBD conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_resenas);
        agregarresena = findViewById(R.id.botonsitoagregarresena);

        biblioteca_y_libro = findViewById(R.id.biblioylibro);
        mensaje = findViewById(R.id.mensajito);

        conexion = new conexionBD(this, utilidades.TABLA_RESEÑAS);

        Bundle Info = getIntent().getExtras();

        if (Info!=null){
            rese = (reseñas) Info.getSerializable("reseñas");
            System.out.println(rese.toString());
            biblioteca_y_libro.setText(rese.getBiblioteca_y_libro());
            mensaje.setText(rese.getMensaje());
            agregarresena.setImageResource(R.drawable.expediente);
            agregarresena.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Vacio()) {
                        Eliminar();
                        Intent intent = new Intent(getApplicationContext(), ListaResenasRecycler.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }else {
            agregarresena.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Vacio()) registrarResenas();
                }
            });
        }



        biblioteca_y_libro = findViewById(R.id.biblioylibro);
        mensaje = findViewById(R.id.mensajito);


    }
    public void Eliminar () {

        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros={rese.getIdrs()};
        db.delete(utilidades.TABLA_RESEÑAS, utilidades.TABLA_CAMPO_IDRS+"=?",parametros);
        Toast.makeText(getApplicationContext(),"RESEÑA ELIMINADA",Toast.LENGTH_LONG).show();
        db.close();
    }

    public boolean Vacio(){
        boolean retorno = true;
        String c1 =biblioteca_y_libro.getText().toString();
        String c2 = mensaje.getText().toString();
        if(c1.isEmpty() || c2.isEmpty()  ){
            mensaje();
            retorno = false;
        }

        return retorno;
    }
    public void mensaje(){
        AlertDialog.Builder Mybuild = new AlertDialog.Builder(this);
        Mybuild.setMessage("NO DEJE CAMPOS VACIOS");
        Mybuild.setTitle("ERROR !CAMPOS VACIOS!");
        Mybuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = Mybuild.create();
        dialog.show();

    }


    private void registrarResenas() {
        conexionBD conn = new conexionBD(this, utilidades.TABLA_RESEÑAS);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(utilidades.TABLA_CAMPO_BIBLIOTECA_Y_LIBRO,biblioteca_y_libro.getText().toString());
        values.put(utilidades.TABLA_CAMPO_MENSAJE,mensaje.getText().toString());


        long VAR = db.insert(utilidades.TABLA_RESEÑAS,utilidades.TABLA_CAMPO_IDRS, values);
        mensajeregistro();


        db.close();

        biblioteca_y_libro.setText("");
        mensaje.setText("");
    }

    public void mensajeregistro(){
        AlertDialog.Builder Mybuild = new AlertDialog.Builder(this);
        Mybuild.setMessage("SE REGISTRO CORRECTAMENTE LA RESEÑA");
        Mybuild.setTitle("!REGISTRO EXITOSO!");
        Mybuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = Mybuild.create();
        dialog.show();

    }
}