package com.easyparkicesi.controller;

import com.easyparkicesi.service.ParqueaderoService;
import com.easyparkicesi.dto.ResumenZonaDTO;
import com.easyparkicesi.model.Parqueadero;
import com.easyparkicesi.model.Zona;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CountDownLatch;
@RestController
@RequestMapping("/api/zonas")
public class ParqueaderoController {

    @Autowired
    private ParqueaderoService service;

    @PutMapping("/{zonaId}/parqueaderos/{parqueaderoId}/toggle")
    public ResponseEntity<String> toggleParqueadero(@PathVariable String zonaId, @PathVariable String parqueaderoId) {
        service.toggleDisponibilidadParqueadero(zonaId, parqueaderoId);
        return ResponseEntity.ok("Estado del parqueadero actualizado");
    }

    @GetMapping("/{zonaId}/ocupados")
    public ResponseEntity<String> contarOcupados(@PathVariable String zonaId) {
        StringBuilder response = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(1);

        service.contarOcupados(zonaId, ocupados -> {
            response.append("Ocupados: ").append(ocupados);
            latch.countDown();
        });

        try {
            latch.await(); // Espera a que se llame el callback
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error esperando el conteo");
        }

        return ResponseEntity.ok(response.toString());
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<ResumenZonaDTO>> getResumenZonas() {
        String firebaseUrl = "https://easypark-9ad4e-default-rtdb.firebaseio.com/zonas.json"; 

        try {
            URL url = new URL(firebaseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Zona> zonas = mapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Zona>>() {});

            List<ResumenZonaDTO> resumenList = new ArrayList<>();

            for (Map.Entry<String, Zona> entry : zonas.entrySet()) {
                Zona zona = entry.getValue();
                int total = zona.getCantidad();
                int ocupados = 0;

                if (zona.getParqueaderos() != null) {
                    for (Parqueadero p : zona.getParqueaderos()) {
                        if (!p.isDisponible()) {
                            ocupados++;
                        }
                    }
                }

                resumenList.add(new ResumenZonaDTO(zona.getId(), ocupados, total));
            }

            return ResponseEntity.ok(resumenList);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
}
