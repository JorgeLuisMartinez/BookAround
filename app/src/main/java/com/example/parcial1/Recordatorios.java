package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.parcial1.adaptadores.ListarResenasAdapter;
import com.example.parcial1.utilidades.utilidades;

import java.util.Calendar;

public class Recordatorios extends AppCompatActivity {

    private static final String TAG = "recordatorios";
    public static final String user="names";
    TextView txtUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
       /* Intent intent = new Intent(this, ListarResenasAdapter.class);
        getConte().startActivity(intent);*/



        conexionBD conn = new conexionBD(this, utilidades.TABLA_RECORDATORIOS);



    }

}