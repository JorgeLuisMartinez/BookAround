package com.example.parcial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial1.entidades.recordatorios;
import com.example.parcial1.utilidades.utilidades;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    public static final String user = "names";
    TextView txtUser;
    conexionBD conexion;
    recordatorios recor;
    private Calendar calendario;
    int HOUR, MINUTE, YEAR, MONTH, DAY;
    String timeSystem, dateSystem;
    private static final String CHANNEL_ID2 ="000" ;
    private static final int CHANNEL_ID =000 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexion = new conexionBD(this, utilidades.TABLA_RECORDATORIOS);
        txtUser = (TextView) findViewById(R.id.textUser);
        Bundle Info = getIntent().getExtras();
        if (Info != null) {
            txtUser.setText("¡Bienvenido " + Info.getString("User") + "!");
        }
        GetDatos();
    }
    public void onBackPressed() {
        final AlertDialog.Builder Mybuild = new AlertDialog.Builder(this);
        Mybuild.setMessage("Esta seguro que desea cerrar sesion?");
        Mybuild.setTitle(":(");
        Mybuild.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent intent = new Intent(getApplicationContext(),login.class);

                startActivity(intent);

            }
        });
        Mybuild.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = Mybuild.create();
        dialog.show();
    }
    public void notificacion() {
        System.out.println("entro notificacion");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID2)
                .setSmallIcon(R.drawable.ic_marcadorsito_foreground)
                .setContentTitle("Recordatorio de entrega")
                .setContentText("Entrega del libro: " + recor.getLibro() + " \nBiblioteca: "+ recor.getBiblioteca())
                .setVibrate(new long[]{100, 250, 100, 500})
                .setColor(Color.GREEN)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(CHANNEL_ID, builder.build());
    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Recordatorio de entrega";
            String description = " Entrega del libro: " + recor.getLibro() + " \nBiblioteca: "+ recor.getBiblioteca();
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID2, name, importance);
            channel.setVibrationPattern(new long[]{100, 250, 100, 500});
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private boolean consultarListaRecordatorios() {
        System.out.println("entro consultar");
        boolean rr = false;
        DateTime();
        String[] parametros = {dateSystem,timeSystem};
        SQLiteDatabase db = conexion.getReadableDatabase();
        recor = null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM "+utilidades.TABLA_RECORDATORIOS +" WHERE "+utilidades.TABLA_CAMPO_FECHA+"=? AND "+utilidades.TABLA_CAMPO_HORA+"=?", parametros);
            if (cursor.moveToFirst()){
                System.out.println("entro ifconsultar");
                recor = new recordatorios();
                recor.setId(String.valueOf(cursor.getInt(0)));
                recor.setBiblioteca(cursor.getString(1));
                recor.setLibro(cursor.getString(2));
                recor.setFecha(cursor.getString(3));
                recor.setHora(cursor.getString(4));
                System.out.println("recordatorio;"+recor.toString());
                cursor.close();
                rr = true;
            }else {
                rr = false;
            }
            System.out.println("salio consultar");
        }catch (Exception e){
            System.out.println("Error consultar todo: "+e);
        }
        return rr;
    }
    public void GetDatos(){
        System.out.println("entro getdatos");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (consultarListaRecordatorios()){
                            System.out.println("entro if getdatos");
                            notificacion();
                            createNotificationChannel();
                        }

                        Thread.sleep(15000);/// ESTA ES LA CANTIDAD DE TIEMPO EN SEGUNDOS DE ESPERA PARA REPETIR EL EVENTO
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
        System.out.println("salio getdatos");
    }

    public void DateTime(){

        System.out.println("entro datatime");
        calendario = Calendar.getInstance();
        HOUR = calendario.get(java.util.Calendar.HOUR_OF_DAY);
        MINUTE = calendario.get(java.util.Calendar.MINUTE);
        YEAR = calendario.get(java.util.Calendar.YEAR);
        MONTH = calendario.get(java.util.Calendar.MONTH);
        DAY = calendario.get(java.util.Calendar.DAY_OF_MONTH);
        timeSystem = String.format("%02d:%02d",HOUR,MINUTE);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, YEAR);
        calendar1.set(Calendar.MONTH, MONTH);
        calendar1.set(Calendar.DATE, DAY);
        String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();
        dateSystem = dateText;
        System.out.println("FECHA Y HORA:"+timeSystem+"-"+dateSystem);
    }


    public void Siguiente(View view) {
        Intent sgt = new Intent(this, MapsActivity.class);
        startActivity(sgt);
    }

    public void Recordatorios(View view) {
        Intent sgt2 = new Intent(this, ListaRecordatoriosRecycler.class);
        startActivity(sgt2);
    }

    public void Resenas(View view) {
        Intent sgt = new Intent(this, ListaResenasRecycler.class);
        startActivity(sgt);
    }
    public void agregarrecordatorio (View view) {
        Intent sgt = new Intent(this, agregar_recordatorio.class);
        startActivity(sgt);
    }
    public void agergarreseñas (View view) {
        Intent sgt = new Intent(this, agregar_resenas.class);
        startActivity(sgt);
    }
    /*public void salir(View view) {
        Intent sgt = new Intent(this, login.class);
        startActivity(sgt);
    }*/
}