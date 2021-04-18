package com.example.parcial1.entidades;

import java.io.Serializable;

public class recordatorios implements Serializable {
    private String id;
    private String biblioteca;
    private String libro;
    private String fecha;
    private String hora;

    public recordatorios() {
    }

    public recordatorios(String id, String biblioteca, String libro, String fecha, String hora) {
        this.id = id;
        this.biblioteca = biblioteca;
        this.libro = libro;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(String biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "recordatorios{" +
                "id='" + id + '\'' +
                ", biblioteca='" + biblioteca + '\'' +
                ", libro='" + libro + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
