package com.easyparkicesi.model;

import java.util.List;

public class Zona {

    private String id;
    private int cantidad;
    private List<Parqueadero> parqueaderos;

    public Zona() {
        // Constructor vacío requerido por Firebase
    }

    public Zona(String id, int cantidad, List<Parqueadero> parqueaderos) {
        this.id = id;
        this.cantidad = cantidad;
        this.parqueaderos = parqueaderos;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Parqueadero> getParqueaderos() {
        return parqueaderos;
    }

    public void setParqueaderos(List<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }

}