package com.exemplo.restaurante.repository;

import com.exemplo.restaurante.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
}
