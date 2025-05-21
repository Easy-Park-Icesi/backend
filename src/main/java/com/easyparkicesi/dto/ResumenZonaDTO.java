package com.easyparkicesi.dto;

/**
 * ResumenZonaDTO es un objeto de transferencia de datos que representa un resumen de una zona de parqueo.
 * Contiene información sobre la zona, la cantidad de parqueaderos ocupados y disponibles.
*/


import com.fasterxml.jackson.annotation.JsonProperty;

public class ResumenZonaDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("parqueaderos_ocupados")
    private int parqueaderosOcupados;

    @JsonProperty("total_de_parqueaderos")
    private int totalParqueaderos;

    public ResumenZonaDTO(String id, int parqueaderosOcupados, int totalParqueaderos) {
        this.id = id;
        this.parqueaderosOcupados = parqueaderosOcupados;
        this.totalParqueaderos = totalParqueaderos;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public int getParqueaderosOcupados() {
        return parqueaderosOcupados;
    }

    public int getTotalParqueaderos() {
        return totalParqueaderos;
    }
}
