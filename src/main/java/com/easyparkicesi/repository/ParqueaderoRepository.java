package com.easyparkicesi.repository;

import com.easyparkicesi.model.Parqueadero;
import com.easyparkicesi.model.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Long> {
    List<Parqueadero> findByDisponibleTrue();
    List<Parqueadero> findByZonaAndDisponibleTrue(Zona zona);
}
