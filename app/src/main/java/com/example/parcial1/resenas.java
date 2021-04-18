package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.parcial1.utilidades.utilidades;

public class resenas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenas);

        conexionBD conn = new conexionBD(this, utilidades.TABLA_RESEÑAS);
    }

    public void Recordatorios (View view) {
        Intent sgt = new Intent(this, Recordatorios.class);
        startActivity(sgt);
    }
    public void Main(View view) {
        Intent sgt = new Intent(this, MainActivity.class );
        startActivity(sgt);
    }
    public void salir(View view) {
        Intent sgt = new Intent(this, login.class);
        startActivity(sgt);
    }
}