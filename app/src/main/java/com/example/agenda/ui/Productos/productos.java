package com.example.agenda.ui.Productos;

import java.io.Serializable;

public class productos  implements Serializable {
    private  String nombre;
    private  Integer precio;


    public productos() {
    }

    public productos(String nombre, Integer precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
