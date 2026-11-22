package com.exemplo.restaurante.repository;

import com.exemplo.restaurante.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
