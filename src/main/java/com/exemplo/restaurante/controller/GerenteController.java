package com.exemplo.restaurante.controller;

import com.exemplo.restaurante.model.Gerente;
import com.exemplo.restaurante.service.GerenteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerentes")
public class GerenteController extends AbstractCrudController<Gerente> {

    public GerenteController(GerenteService service) {
        super(service);
    }
}
