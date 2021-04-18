package com.example.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.parcial1.entidades.recordatorios;
import com.example.parcial1.utilidades.utilidades;

import java.util.Calendar;

public class agregar_recordatorio extends AppCompatActivity {

    ImageButton botonfecha, botonhora;
    Button botonguardar;
    TextView textfecha, texthora;
    EditText textBiblioteca, textlibro;
    recordatorios recor;
    conexionBD conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recordatorio);

        conexion = new conexionBD(this, utilidades.TABLA_RECORDATORIOS);

        botonfecha = findViewById(R.id.botonsitofecha);
        botonhora = findViewById(R.id.botonsitohora);
        textfecha= findViewById(R.id.textFecha);
        texthora = findViewById(R.id.textHora);
        botonguardar = findViewById(R.id.guardarRecor);

        textBiblioteca = findViewById(R.id.textbiblioteca);
        textlibro = findViewById(R.id.textLibro);


        Bundle Info = getIntent().getExtras();

        if (Info!=null){
            recor = (recordatorios) Info.getSerializable("recordatorio");
            System.out.println(recor.toString());
            textBiblioteca.setText(recor.getBiblioteca());
            textlibro.setText(recor.getLibro());
            textfecha.setText(recor.getFecha());
            texthora.setText(recor.getHora());
            botonguardar.setText("ELIMINAR");
            botonguardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Vacio()){
                    Eliminar();
                    Intent intent = new Intent(getApplicationContext(), ListaRecordatoriosRecycler.class);
                    startActivity(intent);
                    finish();
                    }
                }
            });
        }else {
            botonguardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Vacio()) registrarRecordatorios();
                }
            });
        }


        botonfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        botonhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });
    }
    public void Eliminar () {

        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros={recor.getId()};
        db.delete(utilidades.TABLA_RECORDATORIOS, utilidades.TABLA_CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"RECORDATORIO ELIMINADO",Toast.LENGTH_LONG).show();
        db.close();
    }

    public boolean Vacio(){
        boolean retorno = true;
        String c1 =textBiblioteca.getText().toString();
        String c2 = textlibro.getText().toString();
        String c3 = textfecha.getText().toString();
        String c4 = texthora.getText().toString();
        if(c1.isEmpty() || c2.isEmpty() || c3.isEmpty() || c4.isEmpty() ){
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

    public void mensajeregistro(){
        AlertDialog.Builder Mybuild = new AlertDialog.Builder(this);
        Mybuild.setMessage("SE REGISTRO CORRECTAMENTE EL RECORDATORIO");
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

    private void registrarRecordatorios() {
        conexionBD conn = new conexionBD(this, utilidades.TABLA_RECORDATORIOS);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(utilidades.TABLA_CAMPO_BIBLIOTECA,textBiblioteca.getText().toString());
        values.put(utilidades.TABLA_CAMPO_LIBRO,textlibro.getText().toString());
        values.put(utilidades.TABLA_CAMPO_FECHA,textfecha.getText().toString());
        values.put(utilidades.TABLA_CAMPO_HORA,texthora.getText().toString());

        db.insert(utilidades.TABLA_RECORDATORIOS,null, values);
        mensajeregistro();

        db.close();

        textBiblioteca.setText("");
        texthora.setText("");
        textlibro.setText("");
        textfecha.setText("");
    }

    private void handleDateButton() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                textfecha.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();


    }

    protected void handleTimeButton(){
        Calendar calendar = Calendar.getInstance();
        final int HOUR = calendar.get(Calendar.HOUR_OF_DAY);
        final int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24FormatoHora = DateFormat.is24HourFormat(this);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String timeString = String.format("%02d:%02d",hour,minute);
                texthora.setText(timeString);
            }
        }, HOUR,MINUTE, is24FormatoHora);
        timePickerDialog.show();
    }
}