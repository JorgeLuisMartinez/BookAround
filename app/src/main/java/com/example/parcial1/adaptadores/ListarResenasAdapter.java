package com.example.parcial1.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial1.R;
import com.example.parcial1.entidades.reseñas;

import java.util.ArrayList;

public class ListarResenasAdapter extends RecyclerView.Adapter<ListarResenasAdapter.ReseñasViewHolder>
                implements View.OnClickListener {
    ArrayList<reseñas> listaReseña;
    private View.OnClickListener listener;

    public ListarResenasAdapter(ArrayList<reseñas> listaReseñas){
        this.listaReseña = listaReseñas;
    }
    @NonNull
    @Override
    public ReseñasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_resenas,null,false);
        view.setOnClickListener(this);
        return new ReseñasViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListarResenasAdapter.ReseñasViewHolder holder, int position) {
        holder.BIBLIOTECA_Y_LIBRO.setText(listaReseña.get(position).getBiblioteca_y_libro().toString());
        holder.MENSAJE.setText(listaReseña.get(position).getMensaje());

    }

    @Override
    public int getItemCount() {
        return listaReseña.size();

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

    public class ReseñasViewHolder extends RecyclerView.ViewHolder {
        TextView BIBLIOTECA_Y_LIBRO, MENSAJE;
        public ReseñasViewHolder(@NonNull View itemView) {
            super(itemView);
            BIBLIOTECA_Y_LIBRO = (TextView)itemView.findViewById(R.id.BIBLIOTECA_Y_LIBRO);
            MENSAJE = (TextView)itemView.findViewById(R.id.textMENSAJE);

        }
    }
}