package com.easyparkicesi.repository;

import com.easyparkicesi.model.EasyparkIcesi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EasyparkIcesiRepository extends JpaRepository<EasyparkIcesi, Long> {
    List<EasyparkIcesi> findByParqueaderoId(Long parqueaderoId);
}
