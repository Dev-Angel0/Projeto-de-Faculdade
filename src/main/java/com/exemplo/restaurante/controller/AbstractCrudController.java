package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.service.AbstractCrudService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractCrudController<T> {

    private final AbstractCrudService<T> service;

    protected AbstractCrudController(AbstractCrudService<T> service) {
        this.service = service;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public List<T> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public T buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public T criar(@Valid @RequestBody T entidade) {
        return service.criar(entidade);
    }

    @PutMapping("/{id}")
    @Transactional
    public T atualizar(@PathVariable Long id, @Valid @RequestBody T entidade) {
        return service.atualizar(id, entidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
