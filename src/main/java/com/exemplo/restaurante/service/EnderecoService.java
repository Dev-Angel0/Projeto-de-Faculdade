package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.Endereco;
import com.exemplo.restaurante.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends AbstractCrudService<Endereco> {

    public EnderecoService(EnderecoRepository repository) {
        super(repository, "Endereço");
    }

    @Override
    protected void definirId(Endereco entidade, Long id) {
        entidade.setId(id);
    }
}
