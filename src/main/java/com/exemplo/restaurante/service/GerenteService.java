package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.Gerente;
import com.exemplo.restaurante.repository.GerenteRepository;
import org.springframework.stereotype.Service;

@Service
public class GerenteService extends AbstractCrudService<Gerente> {

    public GerenteService(GerenteRepository repository) {
        super(repository, "Gerente");
    }

    @Override
    protected void definirId(Gerente entidade, Long id) {
        entidade.setId(id);
    }
}
