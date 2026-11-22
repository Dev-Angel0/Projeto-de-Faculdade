package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.Categoria;
import com.exemplo.restaurante.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends AbstractCrudService<Categoria> {

    public CategoriaService(CategoriaRepository repository) {
        super(repository, "Categoria");
    }

    @Override
    protected void definirId(Categoria entidade, Long id) {
        entidade.setId(id);
    }
}
