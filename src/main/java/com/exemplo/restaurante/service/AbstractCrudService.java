package com.exemplo.restaurante.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractCrudService<T> {

    private final JpaRepository<T, Long> repository;
    private final String entityName;

    protected AbstractCrudService(JpaRepository<T, Long> repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    protected abstract void definirId(T entidade, Long id);

    public List<T> listarTodos() {
        return repository.findAll();
    }

    public T buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        entityName + " com id " + id + " não encontrado"));
    }

    public T criar(T entidade) {
        return repository.save(entidade);
    }

    public T atualizar(Long id, T entidade) {
        buscarPorId(id);
        definirId(entidade, id);
        return repository.save(entidade);
    }

    public void excluir(Long id) {
        repository.delete(buscarPorId(id));
    }
}
