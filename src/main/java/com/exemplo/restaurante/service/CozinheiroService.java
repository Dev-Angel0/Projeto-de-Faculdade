package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.Cozinheiro;
import com.exemplo.restaurante.repository.CozinheiroRepository;
import org.springframework.stereotype.Service;

@Service
public class CozinheiroService extends AbstractCrudService<Cozinheiro> {

    public CozinheiroService(CozinheiroRepository repository) {
        super(repository, "Cozinheiro");
    }

    @Override
    protected void definirId(Cozinheiro entidade, Long id) {
        entidade.setId(id);
    }
}
