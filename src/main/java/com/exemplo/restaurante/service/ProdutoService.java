package com.exemplo.restaurante.service;

import com.exemplo.restaurante.model.Produto;
import com.exemplo.restaurante.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends AbstractCrudService<Produto> {

    public ProdutoService(ProdutoRepository repository) {
        super(repository, "Produto");
    }

    @Override
    protected void definirId(Produto entidade, Long id) {
        entidade.setId(id);
    }
}
