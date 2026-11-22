package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.model.Produto;
import com.exemplo.restaurante.service.ProdutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController extends AbstractCrudController<Produto> {

    public ProdutoController(ProdutoService service) {
        super(service);
    }
}
