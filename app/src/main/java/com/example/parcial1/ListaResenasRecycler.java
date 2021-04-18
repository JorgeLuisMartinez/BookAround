package com.example.parcial1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial1.adaptadores.ListarResenasAdapter;
import com.example.parcial1.entidades.recordatorios;
import com.example.parcial1.entidades.reseñas;
import com.example.parcial1.utilidades.utilidades;

import java.util.ArrayList;

public class ListaResenasRecycler extends AppCompatActivity {

    ArrayList<reseñas> listaReseña;
    RecyclerView recyclerViewResenas;
    conexionBD conexion;
    String id, Biblioteca_y_libro, Mensaje;
    reseñas rese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenas);


        conexion = new conexionBD(this, utilidades.TABLA_RESEÑAS);

        listaReseña = new ArrayList<>();

        recyclerViewResenas = (RecyclerView) findViewById(R.id.recycler_resenas);
        recyclerViewResenas.setLayoutManager(new LinearLayoutManager(this));

        consultarListaResenas();

        ListarResenasAdapter adapter = new ListarResenasAdapter(listaReseña);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = (listaReseña.get(recyclerViewResenas.getChildAdapterPosition(v)).getIdrs());
                Biblioteca_y_libro = (listaReseña.get(recyclerViewResenas.getChildAdapterPosition(v)).getBiblioteca_y_libro());
                Mensaje = (listaReseña.get(recyclerViewResenas.getChildAdapterPosition(v)).getMensaje());


                rese = new reseñas(id, Biblioteca_y_libro, Mensaje);
                System.out.println(rese.toString());
                Intent intent = new Intent(getApplicationContext(), agregar_resenas.class);
                Bundle Info = new Bundle();
                Info.putSerializable("reseñas",rese);
                intent.putExtras(Info);
                startActivity(intent);
                finish();

            }
        });
        recyclerViewResenas.setAdapter(adapter);
    }



    private void consultarListaResenas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        reseñas Reseña = null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM "+ utilidades.TABLA_RESEÑAS, null);
            while (cursor.moveToNext()){
                Reseña = new reseñas();
                Reseña.setIdrs(String.valueOf(cursor.getInt(0)));
                Reseña.setBiblioteca_y_libro(cursor.getString(1));
                Reseña.setMensaje(cursor.getString(2));
                System.out.println(Reseña.toString());
                listaReseña.add(Reseña);
            }
            cursor.close();
        }catch (Exception e){
            System.out.println("Error consultar todo: "+e);
        }
    }

}