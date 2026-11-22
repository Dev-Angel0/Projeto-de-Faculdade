package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.Cliente;
import com.exemplo.restaurante.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends AbstractCrudService<Cliente> {

    public ClienteService(ClienteRepository repository) {
        super(repository, "Cliente");
    }

    @Override
    protected void definirId(Cliente entidade, Long id) {
        entidade.setId(id);
    }
}
