package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.Cliente;
import com.exemplo.restaurante.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService extends AbstractCrudService<Cliente> {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        super(repository, "Cliente");
        this.repository = repository;
    }

    @Override
    protected void definirId(Cliente entidade, Long id) {
        entidade.setId(id);
    }

    @Override
    @Transactional
    public Cliente atualizar(Long id, Cliente dadosAtualizados) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dadosAtualizados.getNome());
        cliente.setEmail(dadosAtualizados.getEmail());
        cliente.setTelefone(dadosAtualizados.getTelefone());
        return repository.save(cliente);
    }
}
