package com.easyparkicesi.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Zona {

    @Id
    private String id;

    private String nombre;

    private int parqueaderos;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parqueadero> listaParqueaderos;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getParqueaderos() {
        return parqueaderos;
    }

    public void setParqueaderos(int parqueaderos) {
        this.parqueaderos = parqueaderos;
    }

    public List<Parqueadero> getListaParqueaderos() {
        return listaParqueaderos;
    }

    public void setListaParqueaderos(List<Parqueadero> listaParqueaderos) {
        this.listaParqueaderos = listaParqueaderos;
    }
}
