package com.example.parcial1.entidades;

import java.io.Serializable;

public class reseñas implements Serializable {
    private String idrs;
    private String biblioteca_y_libro;
    private String mensaje;

    public reseñas() {
    }

    public reseñas(String idrs, String biblioteca_y_libro, String mensaje) {
        this.idrs = idrs;
        this.biblioteca_y_libro = biblioteca_y_libro;
        this.mensaje = mensaje;
    }

    public String getBiblioteca_y_libro() {
        return biblioteca_y_libro;
    }

    public void setBiblioteca_y_libro(String biblioteca_y_libro) {
        this.biblioteca_y_libro = biblioteca_y_libro;
    }

    public String getIdrs() {
        return idrs;
    }

    public void setIdrs(String idrs) {
        this.idrs = idrs;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return "reseñas{" +
                "idrs='" + idrs + '\'' +
                ", biblioteca_y_libro='" + biblioteca_y_libro + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
