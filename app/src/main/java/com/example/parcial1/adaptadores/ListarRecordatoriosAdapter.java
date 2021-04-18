package com.example.parcial1.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial1.R;
import com.example.parcial1.entidades.recordatorios;

import java.util.ArrayList;

public class ListarRecordatoriosAdapter extends RecyclerView.Adapter<ListarRecordatoriosAdapter.RecordatoriosViewHolder>
                implements View.OnClickListener {
    ArrayList<recordatorios> listaRecordatorio;
    private View.OnClickListener listener;

    public ListarRecordatoriosAdapter ( ArrayList<recordatorios> listaRecordatorios){
        this.listaRecordatorio = listaRecordatorios;
    }
    @NonNull
    @Override
    public RecordatoriosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recordatorios,null,false);
        view.setOnClickListener(this);
        return new RecordatoriosViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListarRecordatoriosAdapter.RecordatoriosViewHolder holder, int position) {
        holder.BIBLIOTECA.setText(listaRecordatorio.get(position).getBiblioteca().toString());
        holder.LIBRO.setText(listaRecordatorio.get(position).getLibro());
        holder.FECHA.setText(listaRecordatorio.get(position).getFecha());
        holder.HORA.setText(listaRecordatorio.get(position).getHora());

    }

    @Override
    public int getItemCount() {
        return listaRecordatorio.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class RecordatoriosViewHolder extends RecyclerView.ViewHolder {
        TextView BIBLIOTECA, LIBRO, FECHA, HORA;
        public RecordatoriosViewHolder(@NonNull View itemView) {
            super(itemView);
            BIBLIOTECA = (TextView)itemView.findViewById(R.id.textBIBLIOTECA);
            LIBRO = (TextView)itemView.findViewById(R.id.textLIBRO);
            FECHA = (TextView)itemView.findViewById(R.id.textFECHA);
            HORA = (TextView)itemView.findViewById(R.id.textHORA);
        }
    }
}
