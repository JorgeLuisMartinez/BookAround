package com.example.parcial1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial1.adaptadores.ListarRecordatoriosAdapter;
import com.example.parcial1.entidades.recordatorios;
import com.example.parcial1.utilidades.utilidades;

import java.util.ArrayList;

import javax.xml.transform.Source;

public class ListaRecordatoriosRecycler extends AppCompatActivity {

    ArrayList<recordatorios> listaRecordatorio;
    RecyclerView recyclerViewRecordatorios;
    conexionBD conexion;
    String id, Biblioteca, Libro, Fecha, Hora;
    recordatorios recor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);



        conexion = new conexionBD(this, utilidades.TABLA_RECORDATORIOS);

        listaRecordatorio = new ArrayList<>();

        recyclerViewRecordatorios = (RecyclerView) findViewById(R.id.recycle_recordatorios);
        recyclerViewRecordatorios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaRecordatorios();

        ListarRecordatoriosAdapter adapter = new ListarRecordatoriosAdapter(listaRecordatorio);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = (listaRecordatorio.get(recyclerViewRecordatorios.getChildAdapterPosition(v)).getId());
                Biblioteca = (listaRecordatorio.get(recyclerViewRecordatorios.getChildAdapterPosition(v)).getBiblioteca());
                Libro = (listaRecordatorio.get(recyclerViewRecordatorios.getChildAdapterPosition(v)).getLibro());
                Fecha = (listaRecordatorio.get(recyclerViewRecordatorios.getChildAdapterPosition(v)).getFecha());
                Hora = (listaRecordatorio.get(recyclerViewRecordatorios.getChildAdapterPosition(v)).getHora());

                recor = new recordatorios(id, Biblioteca, Libro, Fecha, Hora);
                System.out.println(recor.toString());
                Intent intent = new Intent(getApplicationContext(), agregar_recordatorio.class);
                Bundle Info = new Bundle();
                Info.putSerializable("recordatorio",recor);
                intent.putExtras(Info);
                startActivity(intent);
                finish();


            }
        });
        recyclerViewRecordatorios.setAdapter(adapter);
    }



    private void consultarListaRecordatorios() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        recordatorios REcordatorio = null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM "+ utilidades.TABLA_RECORDATORIOS, null);
            while (cursor.moveToNext()){
                REcordatorio = new recordatorios();
                REcordatorio.setId(String.valueOf(cursor.getInt(0)));
                REcordatorio.setBiblioteca(cursor.getString(1));
                REcordatorio.setLibro(cursor.getString(2));
                REcordatorio.setFecha(cursor.getString(3));
                REcordatorio.setHora(cursor.getString(4));
                listaRecordatorio.add(REcordatorio);
            }
            cursor.close();
        }catch (Exception e){
            System.out.println("Error consultar todo: "+e);
        }
    }

    public void GetDatos(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ///GetDatosC();  METODO A EJECUTAR

                        Thread.sleep(10000);/// ESTA ES LA CANTIDAD DE TIEMPO EN SEGUNDOS DE ESPERA PARA REPETIR EL EVENTO
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }

}
