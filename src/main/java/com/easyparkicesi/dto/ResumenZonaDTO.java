package com.easyparkicesi.dto;

/**
 * ResumenZonaDTO es un objeto de transferencia de datos que representa un resumen de una zona de parqueo.
 * Contiene información sobre la zona, la cantidad de parqueaderos ocupados y disponibles.
 */


public class ResumenZonaDTO {
    private String zona;
    private int cantidad;
    private int ocupados;
    private int disponibles;

    // Constructor
    public ResumenZonaDTO(String zona,int cantidad, int ocupados, int disponibles) {
        this.zona = zona;
        this.cantidad = cantidad;
        this.ocupados = ocupados;
        this.disponibles = disponibles;
    }

    // Getters y setters
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }

    public int getOcupados() { return ocupados; }
    public void setOcupados(int ocupados) { this.ocupados = ocupados; }

    public int getDisponibles() { return disponibles; }
    public void setDisponibles(int disponibles) { this.disponibles = disponibles; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad;}
}
