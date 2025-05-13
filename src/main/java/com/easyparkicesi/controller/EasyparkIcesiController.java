package com.easyparkicesi.controller;

import com.easyparkicesi.model.EasyparkIcesi;
import com.easyparkicesi.service.EasyparkIcesiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkings")
public class EasyparkIcesiController {

    @Autowired
    private EasyparkIcesiService easyparkIcesiService;

    // Actualiza el estado de ocupación de un parqueadero
    @PutMapping("/{id}/ocupacion")
    public void actualizarEstado(@PathVariable Long id, @RequestParam boolean ocupado) {
        easyparkIcesiService.actualizarEstado(id, ocupado);
    }

    // Obtiene el historial de ocupación de un parqueadero
    @GetMapping("/{id}/historial")
    public List<EasyparkIcesi> obtenerHistorial(@PathVariable Long id) {
        return easyparkIcesiService.historialDeOcupacion(id);
    }
}
