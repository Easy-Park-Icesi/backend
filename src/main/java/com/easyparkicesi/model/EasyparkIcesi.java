package com.easyparkicesi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EasyparkIcesi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parqueaderoId;  // Relaciona el registro con un parqueadero específico
    private boolean ocupado;     // Estado de ocupación en ese momento
    private LocalDateTime timestamp; // Fecha y hora del registro

    public EasyparkIcesi() {
        this.timestamp = LocalDateTime.now();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParqueaderoId() {
        return parqueaderoId;
    }

    public void setParqueaderoId(Long parqueaderoId) {
        this.parqueaderoId = parqueaderoId;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
