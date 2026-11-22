package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.Entregador;
import com.exemplo.restaurante.repository.EntregadorRepository;
import org.springframework.stereotype.Service;

@Service
public class EntregadorService extends AbstractCrudService<Entregador> {

    public EntregadorService(EntregadorRepository repository) {
        super(repository, "Entregador");
    }

    @Override
    protected void definirId(Entregador entidade, Long id) {
        entidade.setId(id);
    }
}
