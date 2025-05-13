package com.easyparkicesi.service;

import com.easyparkicesi.model.EasyparkIcesi; 
import com.easyparkicesi.model.Parqueadero;
import com.easyparkicesi.model.Zona;
import com.easyparkicesi.repository.EasyparkIcesiRepository;
import com.easyparkicesi.repository.ParqueaderoRepository;
import com.easyparkicesi.repository.ZonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EasyparkIcesiService {

    @Autowired
    private ParqueaderoRepository parqueaderoRepo;

    @Autowired
    private EasyparkIcesiRepository easyparkIcesiRepo;

    @Autowired
    private ZonaRepository zonaRepo;

    // ✅ Actualizar disponibilidad de un parqueadero y registrar el evento
    public void actualizarEstado(Long idParqueadero, boolean ocupado) {
        Parqueadero parqueadero = parqueaderoRepo.findById(idParqueadero)
            .orElseThrow(() -> new IllegalArgumentException("Parqueadero no encontrado con ID: " + idParqueadero));

        parqueadero.setDisponible(!ocupado);
        parqueaderoRepo.save(parqueadero);

        EasyparkIcesi registro = new EasyparkIcesi(); // Crear un nuevo registro
        registro.setParqueaderoId(idParqueadero);
        registro.setOcupado(ocupado);
        easyparkIcesiRepo.save(registro);
    }

    // ✅ Historial de un parqueadero
    public List<EasyparkIcesi> historialDeOcupacion(Long parqueaderoId) {
        return easyparkIcesiRepo.findByParqueaderoId(parqueaderoId);
    }

    // ✅ Todos los parqueaderos disponibles
    public List<Parqueadero> obtenerParqueaderosDisponibles() {
        return parqueaderoRepo.findByDisponibleTrue();
    }

    // ✅ Parqueaderos disponibles por zona
    public List<Parqueadero> obtenerParqueaderosDisponiblesPorZona(String zonaId) {
        Zona zona = zonaRepo.findById(zonaId)
            .orElseThrow(() -> new IllegalArgumentException("Zona no encontrada con ID: " + zonaId));
        return parqueaderoRepo.findByZonaAndDisponibleTrue(zona);
    }
}
