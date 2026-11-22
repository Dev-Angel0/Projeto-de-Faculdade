package com.exemplo.restaurante.repository;

import com.exemplo.restaurante.model.Cozinheiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinheiroRepository extends JpaRepository<Cozinheiro, Long> {
}
