package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.model.Categoria;
import com.exemplo.restaurante.service.CategoriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends AbstractCrudController<Categoria> {

    public CategoriaController(CategoriaService service) {
        super(service);
    }
}
